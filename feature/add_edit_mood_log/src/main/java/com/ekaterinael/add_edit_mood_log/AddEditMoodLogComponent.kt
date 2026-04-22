package com.ekaterinael.add_edit_mood_log

import android.os.Parcelable
import com.ekaterinael.domain.model.Mood
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize
import java.util.Date

interface AddEditMoodLogComponent {
    val model: StateFlow<Model>

    fun onChangeTitle(value: String)

    fun onChangeDescription(value: String)

    fun onChangeMood(value: Mood)

    fun onGoBack()

    fun onClickSave()

    companion object {
        @Parcelize
        data class Model(
            val id: Long?,
            val date: Date?,
            val title: String,
            val description: String,
            val mood: Mood
        ): Parcelable
    }
}