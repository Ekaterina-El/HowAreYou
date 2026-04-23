package com.ekaterinael.mood_list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ekaterinael.core.componentScope
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.mood_list.MoodLogStore.State
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultMoodLogComponent @AssistedInject constructor(
    moodLogStoreFactory: MoodLogStoreFactory,
    @Assisted("componentContext") private val componentContext: ComponentContext,
    @Assisted("onOpenLogToEdit") private val onOpenLogToEdit: (MoodLogDTO) -> Unit,
    @Assisted("goToCreateNewLog") private val goToCreateNewLog: (selectedMood: Mood?) -> Unit
) : MoodLogComponent, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore { moodLogStoreFactory.create() }

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

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("onOpenLogToEdit") onOpenLogToEdit: (MoodLogDTO) -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext,
            @Assisted("goToCreateNewLog") goToCreateNewLog: (selectedMood: Mood?) -> Unit
        ): DefaultMoodLogComponent
    }
}