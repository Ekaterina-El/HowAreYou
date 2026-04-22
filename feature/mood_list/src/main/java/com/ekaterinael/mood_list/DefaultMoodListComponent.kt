package com.ekaterinael.mood_list

import com.arkivanov.decompose.ComponentContext
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.mood_list.MoodListComponent.Companion.Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultMoodListComponent(
    private val componentContext: ComponentContext,
    private val onMoodLogSelected: (MoodLogDTO) -> Unit,
    private val onAddNewLogClicked: (selectedMood: Mood?) -> Unit,
) : MoodListComponent, ComponentContext by componentContext {
    private val _model = MutableStateFlow(Model())

    override val model: StateFlow<Model> = _model.asStateFlow()

    override fun onClickAddNewLog(selectedMood: Mood?) = onAddNewLogClicked(selectedMood)

    override fun onClickByLog(log: MoodLogDTO) = onMoodLogSelected(log)

    override fun onChangeFilterString(newValue: String) {
        TODO("Not yet implemented")
    }
}