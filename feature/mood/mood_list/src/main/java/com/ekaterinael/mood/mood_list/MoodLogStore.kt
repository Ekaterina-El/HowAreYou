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
import com.ekaterinael.core.ext.toShortUserString
import com.ekaterinael.mood.core.MoodUI
import java.util.Date
import java.util.Locale

interface MoodLogStore : Store<MoodLogStore.Intent, MoodLogStore.State, MoodLogStore.Label> {
  data class State(
    val logs: List<MoodListItemUI> = emptyList(),
    val moods: List<MoodUI>,
    val selectedMonth: Date,
  ) {
    fun selectedMonthUserString(locale: Locale): String {
      return selectedMonth.toShortUserString(locale = locale)
    }
  }

  sealed interface Intent {
    data class OnClickByLog(val logId: Long) : Intent

    data class OnClickAddNewLog(val selectedMood: MoodUI) : Intent
  }

  sealed interface Label {
    data class OpenLogToEdit(val logId: Long) : Label

    data class GoToCreateNewLog(val selectedMood: MoodUI) : Label
  }
}
