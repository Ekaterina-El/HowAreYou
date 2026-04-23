package com.ekaterinael.mood_list

import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLogDTO
import kotlinx.coroutines.flow.StateFlow

interface MoodLogComponent {
    val model: StateFlow<MoodLogStore.State>

    fun onClickAddNewLog(selectedMood: Mood)

    fun onClickByLog(log: MoodLogDTO)
}