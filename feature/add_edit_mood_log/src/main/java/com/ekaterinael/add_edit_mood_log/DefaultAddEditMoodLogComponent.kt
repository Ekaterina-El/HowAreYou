package com.ekaterinael.add_edit_mood_log

import com.arkivanov.decompose.ComponentContext
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogComponent.Companion.Model
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLogDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultAddEditMoodLogComponent(
    private val componentContext: ComponentContext,
    moodLog: MoodLogDTO,
    private val onGoBackCallback: () -> Unit
) : AddEditMoodLogComponent, ComponentContext by componentContext {
    private val _model = MutableStateFlow(
        Model(
            id = moodLog.id,
            date = moodLog.date,
            title = moodLog.title,
            description = moodLog.description,
            mood = moodLog.mood
        )
    )

    override val model: StateFlow<Model> = _model.asStateFlow()

    override fun onChangeTitle(value: String) {
        TODO("Not yet implemented")
    }

    override fun onChangeDescription(value: String) {
        TODO("Not yet implemented")
    }

    override fun onChangeMood(value: Mood) {
        TODO("Not yet implemented")
    }

    override fun onGoBack() {
        onGoBackCallback()
    }

    override fun onClickSave() {
        // Todo: Save
        onGoBackCallback()
    }
}