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
import javax.inject.Inject
import kotlinx.coroutines.flow.map

@AppScope
class LocalMoodDataSource
@Inject
constructor(private val mapper: Mapper<MoodLog, MoodLogEntity>, private val dao: MoodLogDao) :
  MoodDataSource {
  override suspend fun add(log: MoodLog) = dao.add(mapper.fromDTO(log))

  override suspend fun update(log: MoodLog) = dao.update(mapper.fromDTO(log))

  override suspend fun removeById(id: Long) = dao.removeById(id)

  override suspend fun getById(id: Long): MoodLog? = dao.getById(id = id)?.let { mapper.toDTO(it) }

  override fun getLogs() = dao.getLog().map(mapper::toDTO)
}
