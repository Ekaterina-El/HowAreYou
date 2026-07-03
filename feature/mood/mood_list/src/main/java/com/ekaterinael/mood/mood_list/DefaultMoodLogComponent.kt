package com.ekaterinael.mood.mood_list

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ekaterinael.core.ext.componentScope
import com.ekaterinael.mood.core.MoodUI
import com.ekaterinael.mood.domain.model.Mood
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
    @Assisted("onOpenLogToEdit") private val onOpenLogToEdit: (moodId: Long) -> Unit,
    @Assisted("goToCreateNewLog") private val goToCreateNewLog: (selectedMood: Mood) -> Unit
) : MoodLogComponent, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore { moodLogStoreFactory.create() }

    override val model: StateFlow<MoodLogStore.State> = store.stateFlow

    init {
        componentScope().launch {
            store.labels.collect {
                when (it) {
                    is MoodLogStore.Label.OpenLogToEdit -> {
                        onOpenLogToEdit(it.logId)
                    }

                    is MoodLogStore.Label.GoToCreateNewLog -> {
                        goToCreateNewLog(it.selectedMood.mood)
                    }
                }
            }
        }
    }

    override fun onClickAddNewLog(selectedMood: MoodUI) {
        store.accept(MoodLogStore.Intent.OnClickAddNewLog(selectedMood))
    }

    override fun onClickByLog(logId: Long?) {
        if (logId == null) {
            Log.w(TAG, "Can`t open log - id is null")
            return
        }

        store.accept(MoodLogStore.Intent.OnClickByLog(logId))
    }

    @[AssistedFactory MoodListScope]
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext,
            @Assisted("onOpenLogToEdit") onOpenLogToEdit: (moodId: Long) -> Unit,
            @Assisted("goToCreateNewLog") goToCreateNewLog: (selectedMood: Mood) -> Unit
        ): DefaultMoodLogComponent
    }

    companion object {
        private const val TAG = "DefaultMoodLogComponent"
    }
}