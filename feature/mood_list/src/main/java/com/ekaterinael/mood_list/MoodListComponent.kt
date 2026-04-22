package com.ekaterinael.mood_list

import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLogDTO
import kotlinx.coroutines.flow.StateFlow

interface MoodListComponent {
    val model: StateFlow<Model>

    fun onClickAddNewLog(selectedMood: Mood?)

    fun onClickByLog(log: MoodLogDTO)

    fun onChangeFilterString(newValue: String)

    companion object {
        data class Model(
            val filterString: String = "",
            val logs: List<MoodLogDTO> = emptyList()
        )
    }
}