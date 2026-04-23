package com.ekaterinael.mode_statistic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ekaterinael.mode_statistic.MoodStatisticStore.State
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultMoodStatisticComponent @AssistedInject constructor(
    moodStatisticStoreFactory: MoodStatisticStoreFactory,
    @Assisted("componentContext") private val componentContext: ComponentContext
) : MoodStatisticComponent, ComponentContext by componentContext {
    private val store: MoodStatisticStore =
        instanceKeeper.getStore { moodStatisticStoreFactory.create() }

    override val model: StateFlow<State> = store.stateFlow

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultMoodStatisticComponent
    }
}