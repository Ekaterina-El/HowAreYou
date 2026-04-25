package com.ekaterinael.mood_list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ekaterinael.mood_list.MoodLogComponent

@Composable
fun MoodLog(component: MoodLogComponent) {
    val model by component.model.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 12.dp)
    ) {
        MoodLogNewState(onSelectMood = component::onClickAddNewLog)
        Spacer(Modifier.height(12.dp))
        MoodLogsList(
            logs = model.logs,
            onSelectLog = component::onClickByLog
        )
    }
}