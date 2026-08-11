/*
 * Copyright 2026 Ekaterina Elshina
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ekaterinael.mood.data.dataSource

import com.ekaterinael.core.di.AppScope
import com.ekaterinael.data.local.dao.MoodLogDao
import com.ekaterinael.data.local.entity.MoodLogEntity
import com.ekaterinael.data.mapper.Mapper
import com.ekaterinael.mood.domain.model.MoodLog
import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Local implementation of [MoodDataSource] that manages mood log data stored in the application's
 * database.
 *
 * @param mapper converts between domain [MoodLog] objects and database [MoodLogEntity] objects.
 * @param dao provides access to stored mood log entries.
 */
@AppScope
class LocalMoodDataSource
@Inject
constructor(private val mapper: Mapper<MoodLog, MoodLogEntity>, private val dao: MoodLogDao) :
  MoodDataSource {
  override suspend fun add(log: MoodLog) = dao.add(mapper.fromDTO(log))

  override suspend fun update(log: MoodLog) = dao.update(mapper.fromDTO(log))

  override suspend fun removeById(id: Long) = dao.removeById(id)

  override suspend fun getById(id: Long): MoodLog? = dao.getById(id = id)?.let { mapper.toDTO(it) }

  override fun getLogs(month: Date): Flow<List<MoodLog>> {
    val calendar = startOfDay(month).apply { set(Calendar.DAY_OF_MONTH, FIRST_DAY_OF_MONTH) }
    val start = calendar.time
    calendar.add(Calendar.MONTH, MONTHS_TO_ADD)
    val end = calendar.time
    return dao.getLogsByMonth(start, end).map(mapper::toDTO)
  }

  override fun hasLogForDay(day: Date): Flow<Boolean> {
    val calendar = startOfDay(day)
    val start = calendar.time
    calendar.add(Calendar.DAY_OF_MONTH, DAYS_TO_ADD)
    val end = calendar.time
    return dao.hasLogInRange(start, end)
  }

  private fun startOfDay(date: Date): Calendar =
    Calendar.getInstance().apply {
      time = date
      set(Calendar.HOUR_OF_DAY, START_OF_DAY_HOUR)
      set(Calendar.MINUTE, START_OF_DAY_MINUTE)
      set(Calendar.SECOND, START_OF_DAY_SECOND)
      set(Calendar.MILLISECOND, START_OF_DAY_MILLISECOND)
    }

  companion object {
    private const val FIRST_DAY_OF_MONTH = 1
    private const val START_OF_DAY_HOUR = 0
    private const val START_OF_DAY_MINUTE = 0
    private const val START_OF_DAY_SECOND = 0
    private const val START_OF_DAY_MILLISECOND = 0
    private const val MONTHS_TO_ADD = 1
    private const val DAYS_TO_ADD = 1
  }
}
