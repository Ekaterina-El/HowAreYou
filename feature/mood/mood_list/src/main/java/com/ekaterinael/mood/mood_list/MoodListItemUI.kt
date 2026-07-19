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
package com.ekaterinael.mood.mood_list

import com.ekaterinael.mood.core.MoodUI
import java.util.Date

/**
 * Represents a mood log entry prepared for display in the mood list.
 *
 * @property id the unique identifier of the mood log, or `null` for a new entry.
 * @property date the date and time associated with the mood log, or `null` if not specified.
 * @property description the user-provided description of the mood.
 * @property mood the mood prepared for display in the user interface.
 */
data class MoodListItemUI(
  val id: Long? = null,
  val date: Date?,
  val description: String,
  val mood: MoodUI,
)
