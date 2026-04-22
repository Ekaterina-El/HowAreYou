package com.ekaterinael.mode_statistic

import com.arkivanov.decompose.ComponentContext
import com.ekaterinael.mode_statistic.MoodStatisticComponent.Companion.Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultMoodStatisticComponent(
    private val componentContext: ComponentContext
) : MoodStatisticComponent, ComponentContext by componentContext {
    private val _model = MutableStateFlow(Model())
    override val model: StateFlow<Model> = _model.asStateFlow()

}