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
package com.ekaterinael.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// TODO: Add correct colors for dark theme
private val DarkColorScheme =
  darkColorScheme(
    background = DarkCharcoal,
    onBackground = WarmLightText,
    surfaceContainer = ElevatedCharcoal,
    onSurface = WarmLightText,
    secondaryContainer = DarkGraphite,
    primary = SoftTeal,
  )

private val LightColorScheme =
  lightColorScheme(
    background = WarmIvoryBackground,
    onBackground = GraphiteText,
    surfaceContainer = SoftIvorySurface,
    onSurface = GraphiteText,
    secondaryContainer = WarmWhiteContainer,
    primary = DeepTealPrimary,
  )

@Composable
fun HowAreYouTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    shapes = Shapes,
    content = content,
  )
}
