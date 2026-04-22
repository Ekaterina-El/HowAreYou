package com.ekaterinael.mood_list.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ekaterinael.mood_list.MoodLogComponent

@Composable
fun MoodLog(component: MoodLogComponent) {
    val model by component.model.collectAsState()

    Text("[Mood Log screen in development]")
}