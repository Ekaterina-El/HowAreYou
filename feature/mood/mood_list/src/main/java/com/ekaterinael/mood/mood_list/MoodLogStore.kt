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

import com.arkivanov.mvikotlin.core.store.Store
import com.ekaterinael.mood.core.model.MoodUI
import java.util.Date

/**
 * Defines the MVI store responsible for processing mood log intents, managing screen state, and
 * publishing one-time labels.
 */
interface MoodLogStore : Store<MoodLogStore.Intent, MoodLogStore.State, MoodLogStore.Label> {
  /**
   * Represents the current state of the mood log screen.
   *
   * @property logs the mood log entries displayed on the screen.
   * @property moods the available mood options.
   * @property selectedMonth the month currently selected for displaying mood logs.
   * @property isNextMonthAvailable whether switching to the next month is currently allowed.
   * @property showAddNewLogWidget whether the widget for adding a new mood log should be shown.
   *   Defaults to `false` so it stays hidden until the database confirms there is no log for today
   *   yet, instead of flashing on screen before disappearing.
   * @property userFirstName the current user's first name, shown in the screen's greeting.
   * @property userPhotoUrl the URL of the current user's profile photo, or `null` if not set.
   */
  data class State(
    val logs: List<MoodListItemUI> = emptyList(),
    val moods: List<MoodUI>,
    val selectedMonth: Date,
    val isNextMonthAvailable: Boolean = false,
    val showAddNewLogWidget: Boolean = false,
    val userFirstName: String = "",
    val userPhotoUrl: String? = null,
  )

  /** Represents user actions handled by the mood log store. */
  sealed interface Intent {

    /**
     * Opens the selected mood log entry.
     *
     * @property logId the identifier of the mood log entry.
     */
    data class OnClickByLog(val logId: Long) : Intent

    /**
     * Starts the creation of a new mood log entry.
     *
     * @property selectedMood the initially selected mood.
     */
    data class OnClickAddNewLog(val selectedMood: MoodUI) : Intent

    /** Switches the displayed mood logs to the previous month. */
    data object OnClickPreviousMonth : Intent

    /** Switches the displayed mood logs to the next month. */
    data object OnClickNextMonth : Intent
  }

  /** Represents one-time events published by the mood log store. */
  sealed interface Label {

    /**
     * Requests navigation to the screen for editing a mood log entry.
     *
     * @property logId the identifier of the mood log entry to edit.
     */
    data class OpenLogToEdit(val logId: Long) : Label

    /**
     * Requests navigation to the screen for creating a new mood log entry.
     *
     * @property selectedMood the initially selected mood.
     */
    data class GoToCreateNewLog(val selectedMood: MoodUI) : Label
  }
}
