package com.ekaterinael.add_edit_mood_log

import com.ekaterinael.domain.model.Mood
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

interface AddEditMoodLogComponent {
    val model: StateFlow<AddEditMoodLogStore.State>

    fun onChangeDate(value: Date)

    fun onChangeTitle(value: String)

    fun onChangeDescription(value: String)

    fun onChangeMood(value: Mood)

    fun onGoBack()

    fun onClickSave()
}