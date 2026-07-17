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

import com.arkivanov.mvikotlin.core.store.Store
import com.ekaterinael.mood.core.MoodUI
import java.util.Date

interface AddEditMoodLogStore :
  Store<AddEditMoodLogStore.Intent, AddEditMoodLogStore.State, AddEditMoodLogStore.Label> {
  sealed class State {
    data object Initial : State()

    data object Loading : State()

    data object LoadingException : State()

    data class Editing(
      val id: Long?,
      val date: Date?,
      val description: String,
      val selectedMood: MoodUI,
      val moods: List<MoodUI>,
    ) : State()
  }

  sealed interface Intent {
    data class OnChangeDescription(val description: String) : Intent

    data class OnChangeMood(val mood: MoodUI) : Intent

    data object OnGoBack : Intent

    data object OnSave : Intent
  }

  sealed interface Label {
    data object OnGoBack : Label

    data object AfterSave : Label
  }
}
