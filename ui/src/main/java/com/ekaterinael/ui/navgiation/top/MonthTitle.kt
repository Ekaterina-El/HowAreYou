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
package com.ekaterinael.ui.navgiation.top

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import com.ekaterinael.ui.helper.rememberShortUserStringFormatedDate
import java.util.Date
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay

private const val MONTH_TITLE_SLIDE_DURATION_MILLIS = 300
private const val MONTH_TITLE_SLIDE_DURATION_MILLIS_LONG =
  MONTH_TITLE_SLIDE_DURATION_MILLIS.toLong()

@Composable
fun MonthTitle(
  selectedMonth: Date,
  modifier: Modifier = Modifier,
  onAnimatingChanged: (Boolean) -> Unit = {},
) {
  var isFirstComposition by remember { mutableStateOf(true) }

  LaunchedEffect(selectedMonth) {
    if (isFirstComposition) {
      isFirstComposition = false
    } else {
      onAnimatingChanged(true)
      delay(MONTH_TITLE_SLIDE_DURATION_MILLIS_LONG.milliseconds)
      onAnimatingChanged(false)
    }
  }

  AnimatedContent(
    targetState = selectedMonth,
    modifier = modifier.clipToBounds(),
    transitionSpec = {
      val offsetSpec = tween<IntOffset>(MONTH_TITLE_SLIDE_DURATION_MILLIS)
      val fadeSpec = tween<Float>(MONTH_TITLE_SLIDE_DURATION_MILLIS)
      if (targetState.after(initialState)) {
        slideInHorizontally(offsetSpec) { width -> width } + fadeIn(fadeSpec) togetherWith
          slideOutHorizontally(offsetSpec) { width -> -width } + fadeOut(fadeSpec)
      } else {
        slideInHorizontally(offsetSpec) { width -> -width } + fadeIn(fadeSpec) togetherWith
          slideOutHorizontally(offsetSpec) { width -> width } + fadeOut(fadeSpec)
      }
    },
    label = "MonthTitleSlide",
  ) { month ->
    Text(
      modifier = Modifier.fillMaxWidth(),
      text = rememberShortUserStringFormatedDate(month),
      style = MaterialTheme.typography.titleLarge,
      color = MaterialTheme.colorScheme.primary,
      textAlign = TextAlign.Center,
    )
  }
}
