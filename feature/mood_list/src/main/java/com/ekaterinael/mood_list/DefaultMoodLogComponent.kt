package com.ekaterinael.mood_list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ekaterinael.core.componentScope
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLog
import com.ekaterinael.mood_list.MoodLogStore.State
import com.ekaterinael.mood_list.di.MoodListScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@MoodListScope
class DefaultMoodLogComponent @AssistedInject constructor(
    moodLogStoreFactory: MoodLogStoreFactory,
    @Assisted("componentContext") private val componentContext: ComponentContext,
    @Assisted("onOpenLogToEdit") private val onOpenLogToEdit: (MoodLog) -> Unit,
    @Assisted("goToCreateNewLog") private val goToCreateNewLog: (selectedMood: Mood?) -> Unit
) : MoodLogComponent, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore { moodLogStoreFactory.create() }

    override val model: StateFlow<State> = store.stateFlow

    init {
        componentScope().launch {
            store.labels.collect {
                when (it) {
                    is MoodLogStore.Label.OpenLogToEdit -> {
//                        onOpenLogToEdit(it.log)
                    }

                    is MoodLogStore.Label.GoToCreateNewLog -> {
                        goToCreateNewLog(it.selectedMood)
                    }
                }
            }
        }
    }

    override fun onClickAddNewLog(selectedMood: Mood) {
        store.accept(MoodLogStore.Intent.OnClickAddNewLog(selectedMood))
    }

    override fun onClickByLog(log: MoodListItemUI) {
        store.accept(MoodLogStore.Intent.OnClickByLog(log))
    }

    @[AssistedFactory MoodListScope]
    interface Factory {
        fun create(
            @Assisted("onOpenLogToEdit") onOpenLogToEdit: (MoodLog) -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext,
            @Assisted("goToCreateNewLog") goToCreateNewLog: (selectedMood: Mood?) -> Unit
        ): DefaultMoodLogComponent
    }
}