package com.ekaterinael.add_edit_mood_log

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogStore.Intent
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogStore.State
import com.ekaterinael.core.componentScope
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLogDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultAddEditMoodLogComponent(
    private val componentContext: ComponentContext,
    moodLog: MoodLogDTO,
    private val onGoBackCallback: () -> Unit
) : AddEditMoodLogComponent, ComponentContext by componentContext {

    private lateinit var store: AddEditMoodLogStore
    override val model: StateFlow<State> = store.stateFlow

    init {
        componentScope().launch {
            store.labels.collect {
                when (it) {
                    AddEditMoodLogStore.Label.AfterSave,
                    AddEditMoodLogStore.Label.OnGoBack -> onGoBackCallback
                }
            }
        }
    }

    override fun onChangeDate(value: Date) = store.accept(Intent.OnChangeDate(date = value))
    override fun onChangeTitle(value: String) = store.accept(Intent.OnChangeTitle(title = value))

    override fun onChangeDescription(value: String) {
        store.accept(Intent.OnChangeDescription(description = value))
    }

    override fun onChangeMood(value: Mood) {
        store.accept(Intent.OnChangeMood(mood = value))
    }

    override fun onGoBack() = store.accept(Intent.OnGoBack)

    override fun onClickSave() = store.accept(Intent.OnSave)
}