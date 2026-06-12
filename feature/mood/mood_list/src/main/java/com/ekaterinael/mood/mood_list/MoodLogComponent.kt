package com.ekaterinael.mood.mood_list

import com.ekaterinael.mood.core.MoodUI
import kotlinx.coroutines.flow.StateFlow

interface MoodLogComponent {
    val model: StateFlow<MoodLogStore.State>

    fun onClickAddNewLog(selectedMood: MoodUI)

    fun onClickByLog(log: MoodListItemUI)
}