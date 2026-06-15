

package com.ekaterinael.mood.add_edit_mood_log.presentation

import com.ekaterinael.mood.core.MoodUI
import kotlinx.coroutines.flow.StateFlow

interface AddEditMoodLogComponent {
    val state: StateFlow<AddEditMoodLogStore.State>

    fun onChangeDescription(value: String)

    fun onChangeMood(value: MoodUI)

    fun onGoBack()

    fun onClickSave()
}