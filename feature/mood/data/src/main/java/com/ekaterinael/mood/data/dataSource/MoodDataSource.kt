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

import com.ekaterinael.mood.domain.model.MoodLog
import java.util.Date
import kotlinx.coroutines.flow.Flow

/** Defines operations for accessing and modifying mood log data. */
interface MoodDataSource {

  /**
   * Adds a new mood log entry.
   *
   * @param log the mood log entry to add.
   */
  suspend fun add(log: MoodLog)

  /**
   * Updates an existing mood log entry.
   *
   * @param log the mood log entry to update.
   */
  suspend fun update(log: MoodLog)

  /**
   * Removes a mood log entry by its identifier.
   *
   * @param id the identifier of the mood log entry to remove.
   */
  suspend fun removeById(id: Long)

  /**
   * Returns a mood log entry by its identifier.
   *
   * @param id the identifier of the mood log entry.
   * @return the matching mood log entry, or `null` if it does not exist.
   */
  suspend fun getById(id: Long): MoodLog?

  /**
   * Observes mood log entries created within the specified month.
   *
   * @param month a date within the month to observe.
   * @return a [Flow] that emits the current list of mood log entries within the month.
   */
  fun getLogs(month: Date): Flow<List<MoodLog>>
}
