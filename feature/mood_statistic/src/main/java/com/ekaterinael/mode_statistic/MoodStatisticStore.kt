package com.ekaterinael.mode_statistic

import com.arkivanov.mvikotlin.core.store.Store
import com.ekaterinael.domain.model.MoodLog

interface MoodStatisticStore:
    Store<Nothing, MoodStatisticStore.State, Nothing> {
    data class State(
        val logs: List<MoodLog> = emptyList()
    )
}