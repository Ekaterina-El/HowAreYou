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
package com.ekaterinael.profile.data.dataSource

import com.ekaterinael.profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow

/** Defines operations for accessing the current user's profile data. */
interface ProfileDataSource {
  /**
   * Observes the current user's profile.
   *
   * @return a flow that emits the current profile, or `null` if it hasn't been set up yet.
   */
  fun getProfile(): Flow<Profile?>
}
