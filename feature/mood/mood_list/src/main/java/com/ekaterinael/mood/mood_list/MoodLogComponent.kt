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

import com.ekaterinael.mood.core.model.MoodUI
import kotlinx.coroutines.flow.StateFlow

/** Defines the state and user actions available on the mood log screen. */
interface MoodLogComponent {

  /** Observable state of the mood log screen. */
  val model: StateFlow<MoodLogStore.State>

  /**
   * Opens the screen for creating a new mood log with the specified mood.
   *
   * @param selectedMood the initially selected mood.
   */
  fun onClickAddNewLog(selectedMood: MoodUI)

  /**
   * Opens the specified mood log entry for editing.
   *
   * @param logId the identifier of the mood log entry, or `null` if unavailable.
   */
  fun onClickByLog(logId: Long?)

  /** Switches the displayed mood logs to the previous month. */
  fun onClickPreviousMonth()

  /** Switches the displayed mood logs to the next month. */
  fun onClickNextMonth()
}
