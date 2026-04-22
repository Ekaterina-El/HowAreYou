package com.ekaterinael.mode_statistic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ekaterinael.mode_statistic.MoodStatisticStore.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultMoodStatisticComponent(
    private val componentContext: ComponentContext
) : MoodStatisticComponent, ComponentContext by componentContext {
    private lateinit var store: MoodStatisticStore
    override val model: StateFlow<State> = store.stateFlow
}