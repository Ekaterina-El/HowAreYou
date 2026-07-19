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
package com.ekaterinael.ui.effects.gradient

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

/**
 * Draws a triangular gradient using separate colors for the top, left, and right vertices.
 *
 * @param colorTop the color applied to the top vertex.
 * @param colorLeft the color applied to the left vertex.
 * @param colorRight the color applied to the right vertex.
 */
fun DrawScope.triangleGradient(colorTop: Color, colorLeft: Color, colorRight: Color) {
  drawRect(
    brush =
      Brush.radialGradient(
        colors = listOf(colorTop, Color.Transparent),
        center = Offset(size.width / 2, 0f),
        radius = size.width,
      )
  )

  drawRect(
    brush =
      Brush.radialGradient(
        colors = listOf(colorLeft, Color.Transparent),
        center = Offset(0f, size.height),
        radius = size.width * 0.9f,
      )
  )

  drawRect(
    brush =
      Brush.radialGradient(
        colors = listOf(colorRight, Color.Transparent),
        center = Offset(size.width, size.height),
        radius = size.width * 0.9f,
      )
  )
}
