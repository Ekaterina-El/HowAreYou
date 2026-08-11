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
package com.ekaterinael.mood.domain.repository

import com.ekaterinael.mood.domain.model.MoodLog
import java.util.Date
import kotlinx.coroutines.flow.Flow

/** Repository that manages mood logs data. */
interface MoodRepository {
  /**
   * Adds a new mood log entry.
   *
   * @param moodLog the mood log to be inserted
   */
  suspend fun addNewLog(moodLog: MoodLog)

  /**
   * Update an existing log entry.
   *
   * @param moodLog the mood log to be inserted
   */
  suspend fun editLog(moodLog: MoodLog)

  /** Delete an existing log entry by ID. */
  suspend fun removeLog(id: Long)

  /**
   * Returns a reactive stream of mood logs created within the specified month.
   *
   * @param month a date within the month to observe.
   * @return a flow that emits the current list of mood logs within the month.
   */
  fun getLogs(month: Date): Flow<List<MoodLog>>

  /**
   * Returns a reactive stream indicating whether a mood log exists for the specified day.
   *
   * @param day a date within the day to check.
   * @return a flow that emits `true` while a mood log entry exists for that day.
   */
  fun hasLogForDay(day: Date): Flow<Boolean>

  /** Get a mood logs by ID. */
  suspend fun getLogById(id: Long): MoodLog?
}
