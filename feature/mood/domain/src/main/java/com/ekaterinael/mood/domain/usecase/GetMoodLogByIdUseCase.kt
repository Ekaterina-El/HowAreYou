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
package com.ekaterinael.mood.domain.usecase

import com.ekaterinael.mood.domain.repository.MoodRepository

/**
 * Retrieves a mood log entry by its identifier.
 *
 * @param repository the repository used to access mood log data.
 */
class GetMoodLogByIdUseCase(private val repository: MoodRepository) {
  /**
   * Retrieves a mood log entry by its identifier.
   *
   * @param id the identifier of the mood log entry.
   * @return the matching mood log entry, or `null` if it does not exist.
   */
  suspend operator fun invoke(id: Long) = repository.getLogById(id)
}
