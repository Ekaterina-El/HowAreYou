package com.ekaterinael.mood.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MoodSelector(
    modifier: Modifier = Modifier,
    moods: List<MoodUI>,
    selectedMood: MoodUI? = null,
    onSelectMood: (MoodUI) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        moods.forEach { mood ->
            MoodLogStateItem(
                mood = mood,
                isDeselected = selectedMood?.let { it != mood } ?: false,
                onClick = onSelectMood
            )
        }
    }
}