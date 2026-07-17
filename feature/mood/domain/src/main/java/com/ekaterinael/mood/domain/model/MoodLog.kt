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
package com.ekaterinael.mood.domain.model

import java.util.Date

/**
 * A Data Transfer Object representing a mood log entry.
 *
 * @property id the record's unique identifier
 * @property date the record's creation date
 * @property description a detailed description: what happened, thoughts, events
 * @property mood the user's current mood, represented as [com.ekaterinael.mood.domain.model.Mood]
 */
data class MoodLog(
  val id: Long? = null,
  val date: Date? = null,
  val description: String = "",
  val mood: Mood = Mood.UNKNOWN,
)
