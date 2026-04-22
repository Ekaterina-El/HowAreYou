package com.ekaterinael.mood_list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ekaterinael.core.componentScope
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.mood_list.MoodLogStore.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
 class DefaultMoodLogComponent(
    private val componentContext: ComponentContext,
    private val onOpenLogToEdit: (MoodLogDTO) -> Unit,
    private val goToCreateNewLog: (selectedMood: Mood?) -> Unit
) : MoodLogComponent, ComponentContext by componentContext {
    private lateinit var store: MoodLogStore
    override val model: StateFlow<State> = store.stateFlow

    init {
        componentScope().launch {
            store.labels.collect {
                when (it) {
                    is MoodLogStore.Label.OpenLogToEdit -> {
                        onOpenLogToEdit(it.log)
                    }
                    is MoodLogStore.Label.GoToCreateNewLog -> {
                        goToCreateNewLog(it.selectedMood)
                    }
                }
            }
        }
    }

    override fun onClickAddNewLog(selectedMood: Mood?) {
        store.accept(MoodLogStore.Intent.OnClickAddNewLog(selectedMood))
    }

    override fun onClickByLog(log: MoodLogDTO) {
        store.accept(MoodLogStore.Intent.OnClickByLog(log))
    }
}