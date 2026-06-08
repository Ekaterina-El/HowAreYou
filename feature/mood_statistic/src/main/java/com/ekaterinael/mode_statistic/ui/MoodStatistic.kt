package com.ekaterinael.mode_statistic.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ekaterinael.mode_statistic.MoodStatisticComponent

@Composable
fun MoodStatistic(component: MoodStatisticComponent) {
    val model by component.model.collectAsState()

    Text(model.data.data)
}