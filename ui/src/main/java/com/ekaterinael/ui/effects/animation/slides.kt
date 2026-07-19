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
package com.ekaterinael.ui.effects.animation

import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimator

/**
 * Creates a stack animation that slides content upward from the bottom.
 *
 * @return a stack animator configured with a vertical slide transition.
 */
fun slideFromBottom() = verticalSlide(multiplier = 1f)

/**
 * Creates a vertical stack slide animation.
 *
 * @param multiplier controls the slide direction and distance. Positive values slide from the
 *   bottom, while negative values slide from the top.
 * @return a stack animator configured with the vertical slide transition.
 */
fun verticalSlide(multiplier: Float): StackAnimator {
  return stackAnimator(animationSpec = tween(DEFAULT_ANIMATION_DURATION)) { factor, _, content ->
    content(Modifier.offsetYFactor(factor * multiplier))
  }
}

private const val DEFAULT_ANIMATION_DURATION = 300

private fun Modifier.offsetYFactor(factor: Float): Modifier = layout { measurable, constraints ->
  val placeable = measurable.measure(constraints)

  layout(placeable.width, placeable.height) {
    placeable.placeRelative(x = 0, y = (placeable.width * factor).toInt())
  }
}
