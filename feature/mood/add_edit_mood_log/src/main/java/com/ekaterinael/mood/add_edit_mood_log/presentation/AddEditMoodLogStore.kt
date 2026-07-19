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

/**
 * Defines the MVI store responsible for managing the mood log editor state, handling user intents,
 * and publishing one-time labels.
 */
interface AddEditMoodLogStore :
  Store<AddEditMoodLogStore.Intent, AddEditMoodLogStore.State, AddEditMoodLogStore.Label> {

  /** Represents the current state of the mood log editor. */
  sealed class State {

    /** Represents the initial editor state. */
    data object Initial : State()

    /** Indicates that mood log data is being loaded. */
    data object Loading : State()

    /** Indicates that an error occurred while loading mood log data. */
    data object LoadingException : State()

    /**
     * Represents the editable mood log data.
     *
     * @property id the mood log identifier, or `null` for a new entry.
     * @property date the date and time associated with the mood log.
     * @property description the current mood log description.
     * @property selectedMood the currently selected mood.
     * @property moods the available mood options.
     */
    data class Editing(
      val id: Long?,
      val date: Date?,
      val description: String,
      val selectedMood: MoodUI,
      val moods: List<MoodUI>,
    ) : State()
  }

  /** Represents user actions handled by the mood log editor store. */
  sealed interface Intent {

    /**
     * Updates the mood log description.
     *
     * @property description the new description value.
     */
    data class OnChangeDescription(val description: String) : Intent

    /**
     * Updates the selected mood.
     *
     * @property mood the newly selected mood.
     */
    data class OnChangeMood(val mood: MoodUI) : Intent

    /** Requests navigation back from the editor. */
    data object OnGoBack : Intent

    /** Requests saving the current mood log entry. */
    data object OnSave : Intent
  }

  /** Represents one-time events published by the mood log editor store. */
  sealed interface Label {

    /** Requests navigation back from the editor. */
    data object OnGoBack : Label

    /** Indicates that the mood log entry was saved successfully. */
    data object AfterSave : Label
  }
}
