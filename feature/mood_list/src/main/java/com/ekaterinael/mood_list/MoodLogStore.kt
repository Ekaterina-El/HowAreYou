package com.ekaterinael.mood_list

import com.arkivanov.mvikotlin.core.store.Store
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.utils.toShortUserString
import java.util.Date
import java.util.Locale

interface MoodLogStore: Store<MoodLogStore.Intent, MoodLogStore.State, MoodLogStore.Label> {
    data class State(
        val logs: List<MoodListItemUI> = emptyList(),
        val selectedMonth: Date
    ) {
        fun selectedMonthUserString(locale: Locale): String {
            return selectedMonth.toShortUserString(locale = locale)
        }
    }

    sealed interface Intent {
        data class OnClickByLog(val log: MoodListItemUI): Intent
        data class OnClickAddNewLog(val selectedMood: Mood?): Intent
    }

    sealed interface Label {
        data class OpenLogToEdit(val log: MoodListItemUI): Label
        data class GoToCreateNewLog(val selectedMood: Mood?): Label
    }
}