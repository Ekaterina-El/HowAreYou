package com.ekaterinael.mood.mood_statistic

import com.arkivanov.mvikotlin.core.store.Store

interface MoodStatisticStore:
    Store<Nothing, MoodStatisticStore.State, Nothing> {
    data class State(val data: MoodStatisticUI)
}