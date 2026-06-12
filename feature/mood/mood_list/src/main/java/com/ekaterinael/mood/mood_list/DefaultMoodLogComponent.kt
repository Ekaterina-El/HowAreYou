package com.ekaterinael.mood.mood_list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ekaterinael.core.ext.componentScope
import com.ekaterinael.mood.core.MoodUI
import com.ekaterinael.mood.domain.model.Mood
import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.mood_list.di.MoodListScope
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
    @Assisted("goToCreateNewLog") private val goToCreateNewLog: (selectedMood: MoodUI?) -> Unit
) : MoodLogComponent, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore { moodLogStoreFactory.create() }

    override val model: StateFlow<MoodLogStore.State> = store.stateFlow

    init {
        componentScope().launch {
            store.labels.collect {
                when (it) {
                    is MoodLogStore.Label.OpenLogToEdit -> {
//                        onOpenLogToEdit(it.log) TODO: поправить
                    }

                    is MoodLogStore.Label.GoToCreateNewLog -> {
                        goToCreateNewLog(it.selectedMood)
                    }
                }
            }
        }
    }

    override fun onClickAddNewLog(selectedMood: MoodUI) {
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