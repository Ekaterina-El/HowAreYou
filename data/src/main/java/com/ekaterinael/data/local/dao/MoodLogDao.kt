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
package com.ekaterinael.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.ekaterinael.data.local.entity.MoodLogEntity
import java.util.Date
import kotlinx.coroutines.flow.Flow

/** Provides database access operations for mood log entries. */
@Dao
interface MoodLogDao : BaseDao<MoodLogEntity> {
  /**
   * Observes mood log entries created within the specified date range.
   *
   * @param start the inclusive lower bound of the range.
   * @param end the exclusive upper bound of the range.
   * @return a flow that emits the current list of mood log entities within the range.
   */
  @Query("SELECT * FROM mood_log WHERE date >= :start AND date < :end ORDER BY date DESC")
  fun getLogsByMonth(start: Date, end: Date): Flow<List<MoodLogEntity>>

  /**
   * Observes whether at least one mood log entry exists within the specified date range.
   *
   * @param start the inclusive lower bound of the range.
   * @param end the exclusive upper bound of the range.
   * @return a flow that emits `true` while a mood log entry exists within the range.
   */
  @Query("SELECT EXISTS(SELECT 1 FROM mood_log WHERE date >= :start AND date < :end)")
  fun hasLogInRange(start: Date, end: Date): Flow<Boolean>

  /**
   * Returns a mood log entry by its identifier.
   *
   * @param id the identifier of the mood log entry.
   * @return the matching entity, or `null` if it does not exist.
   */
  @Query("SELECT * FROM mood_log WHERE id = :id") suspend fun getById(id: Long): MoodLogEntity?

  /**
   * Removes a mood log entry by its identifier.
   *
   * @param id the identifier of the mood log entry to remove.
   */
  @Query("DELETE FROM mood_log WHERE id = :id") suspend fun removeById(id: Long)
}
