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
package com.ekaterinael.mood.add_edit_mood_log.presentation

import com.ekaterinael.mood.core.MoodUI
import kotlinx.coroutines.flow.StateFlow

/** Defines the state and user actions available on the mood log editor screen. */
interface AddEditMoodLogComponent {
  /** Observable state of the mood log editor screen. */
  val state: StateFlow<AddEditMoodLogStore.State>

  /**
   * Updates the mood log description.
   *
   * @param value the new description value.
   */
  fun onChangeDescription(value: String)

  /**
   * Updates the selected mood.
   *
   * @param value the newly selected mood.
   */
  fun onChangeMood(value: MoodUI)

  /** Navigates back from the mood log editor. */
  fun onGoBack()

  /** Saves the current mood log entry. */
  fun onClickSave()
}
