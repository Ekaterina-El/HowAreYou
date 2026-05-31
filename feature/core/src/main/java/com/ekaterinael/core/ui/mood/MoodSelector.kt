package com.ekaterinael.core.ui.mood

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ekaterinael.domain.model.Mood

@Composable
fun MoodSelector(
    modifier: Modifier = Modifier,
    moods: List<Mood>,
    selectedMood: Mood? = null,
    onSelectMood: (Mood) -> Unit
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