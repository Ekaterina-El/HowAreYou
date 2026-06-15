package com.ekaterinael.mood.add_edit_mood_log.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.ekaterinael.mood.core.MoodUI
import java.util.Date

interface AddEditMoodLogStore :
    Store<AddEditMoodLogStore.Intent, AddEditMoodLogStore.State, AddEditMoodLogStore.Label> {
    sealed class State {
        data object Initial: State()
        data object Loading: State()
        data object LoadingException: State()
        data class Editing(
            val id: Long?,
            val date: Date?,
            val description: String,
            val selectedMood: MoodUI,
            val moods: List<MoodUI>,
        ): State()
    }

    sealed interface Intent {
        data class OnChangeDescription(val description: String): Intent
        data class OnChangeMood(val mood: MoodUI): Intent
        data object OnGoBack: Intent
        data object OnSave: Intent
    }

    sealed interface Label {
        data object OnGoBack: Label
        data object AfterSave: Label
    }
}