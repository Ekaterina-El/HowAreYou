package com.ekaterinael.mood.mood_statistic

import kotlinx.coroutines.flow.StateFlow

interface MoodStatisticComponent {
    val model: StateFlow<MoodStatisticStore.State>

    // todo: add filters
}