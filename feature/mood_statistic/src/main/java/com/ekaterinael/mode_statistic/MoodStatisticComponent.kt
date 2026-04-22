package com.ekaterinael.mode_statistic

import kotlinx.coroutines.flow.StateFlow

interface MoodStatisticComponent {
    val model: StateFlow<MoodStatisticStore.State>

    // todo: add filters
}