package com.ekaterinael.add_edit_mood_log

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogStore.Intent
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogStore.State
import com.ekaterinael.core.componentScope
import com.ekaterinael.domain.model.Mood
import com.ekaterinael.domain.model.MoodLog
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultAddEditMoodLogComponent @AssistedInject constructor(
    addEditMoodLogStoreFactory: AddEditMoodLogStoreFactory,
    @Assisted("componentContext") private val componentContext: ComponentContext,
    @Assisted("moodLog") moodLog: MoodLog,
    @Assisted("onGoBackCallback") private val onGoBackCallback: () -> Unit
) : AddEditMoodLogComponent, ComponentContext by componentContext {

    private val store: AddEditMoodLogStore = instanceKeeper.getStore {
        addEditMoodLogStoreFactory.create(moodLog)
    }
    override val model: StateFlow<State> = store.stateFlow

    init {
        componentScope().launch {
            store.labels.collect {
                when (it) {
                    AddEditMoodLogStore.Label.AfterSave,
                    AddEditMoodLogStore.Label.OnGoBack -> onGoBackCallback()
                }
            }
        }
    }

    override fun onChangeDescription(value: String) {
        store.accept(Intent.OnChangeDescription(description = value))
    }

    override fun onChangeMood(value: Mood) {
        store.accept(Intent.OnChangeMood(mood = value))
    }

    override fun onGoBack() = store.accept(Intent.OnGoBack)

    override fun onClickSave() = store.accept(Intent.OnSave)

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext,
            @Assisted("moodLog") moodLog: MoodLog,
            @Assisted("onGoBackCallback") onGoBackCallback: () -> Unit
        ): DefaultAddEditMoodLogComponent
    }
}