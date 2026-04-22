package com.ekaterinael.add_edit_mood_log

import com.arkivanov.mvikotlin.core.store.Store
import com.ekaterinael.domain.model.Mood
import java.util.Date

interface AddEditMoodLogStore :
    Store<AddEditMoodLogStore.Intent, AddEditMoodLogStore.State, AddEditMoodLogStore.Label> {
    data class State(
        val id: Long?,
        val date: Date?,
        val title: String,
        val description: String,
        val mood: Mood
    )

    sealed interface Intent {
        data class OnChangeDate(val date: Date): Intent
        data class OnChangeTitle(val title: String): Intent
        data class OnChangeDescription(val description: String): Intent
        data class OnChangeMood(val mood: Mood): Intent
        data object OnGoBack: Intent
        data object OnSave: Intent
    }

    sealed interface Label {
        data object OnGoBack: Label
        data object AfterSave: Label
    }
}