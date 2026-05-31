package com.ekaterinael.mood_list.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ekaterinael.core.ui.mood.color
import com.ekaterinael.domain.model.Mood

@Composable
fun MoodLogStateItem(mood: Mood, onSelectMood: (Mood) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        val alpha by animateFloatAsState(
            targetValue = if (isPressed) 0.8f else 1f,
            label = "tintAlpha"
        )
        val colorWithAlpha by remember {
            derivedStateOf { mood.color().copy(alpha = alpha) }
        }

        Image(
            modifier = Modifier
                .size(50.dp)
                .alpha(alpha)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onSelectMood(mood)
                },
            painter = painterResource(mood.imageResId),
            contentDescription = null,
        )
        Spacer(Modifier.height(5.dp))
        Text(
            modifier = Modifier.alpha(alpha),
            text = stringResource(mood.titleResId).lowercase(),
            style = MaterialTheme.typography.labelSmall,
            color = colorWithAlpha
        )
    }
}