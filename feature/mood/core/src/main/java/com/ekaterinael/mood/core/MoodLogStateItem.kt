/*
 * Copyright 2026 Ekaterina Elshina
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ekaterinael.mood.core

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekaterinael.ui.theme.HowAreYouTheme

@Composable
fun MoodLogStateItem(
  modifier: Modifier = Modifier,
  isDeselected: Boolean = false,
  mood: MoodUI,
  onClick: (MoodUI) -> Unit,
) {
  val pressedAlpha = PRESSED_MOOD_LOG_ITEM_ALPHA
  val deselectedAlpha = DESELECTED_MOOD_LOG_ITEM_ALPHA
  val interactionSource = remember { MutableInteractionSource() }
  val isPressed by interactionSource.collectIsPressedAsState()

  val alpha by
    animateFloatAsState(
      targetValue = if (isPressed) pressedAlpha else if (isDeselected) deselectedAlpha else 1f,
      label = "tintAlpha",
    )

  val moodColor = remember { mood.color }
  val colorWithAlpha by remember(mood) { derivedStateOf { moodColor.copy(alpha = alpha) } }

  Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
    Box(
      Modifier.size(50.dp).drawBehind {
        drawCircle(
          brush =
            Brush.radialGradient(
              colors = listOf(moodColor.copy(alpha = 0.24f), Color.Transparent),
              radius = size.maxDimension,
            ),
          radius = size.maxDimension,
        )
      }
    ) {
      Image(
        modifier =
          Modifier.fillMaxSize().alpha(alpha).clickable(
            interactionSource = interactionSource,
            indication = null,
          ) {
            onClick(mood)
          },
        painter = painterResource(mood.imageResId),
        contentDescription = null,
      )
    }

    Spacer(Modifier.height(5.dp))
    Text(
      modifier = Modifier.alpha(alpha),
      text = stringResource(mood.titleResId).lowercase(),
      style = MaterialTheme.typography.labelLarge,
      color = colorWithAlpha,
    )
  }
}

@Preview
@Composable
private fun MoodLogStateItemPreview() {
  HowAreYouTheme {
    Box(modifier = Modifier.size(100.dp), contentAlignment = Alignment.Center) {
      MoodLogStateItem(mood = MoodUI.Good, onClick = {})
    }
  }
}
