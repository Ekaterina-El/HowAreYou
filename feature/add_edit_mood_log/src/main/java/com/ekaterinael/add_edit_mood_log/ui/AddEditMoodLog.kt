package com.ekaterinael.add_edit_mood_log.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ekaterinael.add_edit_mood_log.AddEditMoodLogComponent

@Composable
fun AddEditMoodLog(component: AddEditMoodLogComponent) {
    val model by component.model.collectAsState()

    Text("[Add/edit mood log in development]")
}