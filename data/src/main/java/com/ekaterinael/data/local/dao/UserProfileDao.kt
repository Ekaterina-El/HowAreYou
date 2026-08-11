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
import com.ekaterinael.data.local.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

/** Provides database access operations for the current user's profile. */
@Dao
interface UserProfileDao {
  /**
   * Observes the stored profile.
   *
   * @return a flow that emits the current profile, or `null` if it hasn't been set up yet.
   */
  @Query("SELECT * FROM user_profile WHERE id = 1") fun getProfile(): Flow<UserProfileEntity?>
}
