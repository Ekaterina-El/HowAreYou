package com.ekaterinael.mode_statistic

import com.ekaterinael.domain.model.MoodLogDTO
import kotlinx.coroutines.flow.StateFlow

interface MoodStatisticComponent {
    val model: StateFlow<Model>

    // todo: add filters

    companion object {
        data class Model(
            val logs: List<MoodLogDTO> = emptyList()
        )
    }
}