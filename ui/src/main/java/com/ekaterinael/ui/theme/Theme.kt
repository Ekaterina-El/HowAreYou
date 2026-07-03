package com.ekaterinael.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// TODO: Add correct colors for dark theme
private val DarkColorScheme = darkColorScheme(
    background = DarkCharcoal,
    onBackground = WarmLightText,
    surfaceContainer = ElevatedCharcoal,
    onSurface = WarmLightText,
    secondaryContainer = DarkGraphite,
    primary = SoftTeal,
)

private val LightColorScheme = lightColorScheme(
    background = WarmIvoryBackground,
    onBackground = GraphiteText,
    surfaceContainer = SoftIvorySurface,
    onSurface = GraphiteText,
    secondaryContainer = WarmWhiteContainer,
    primary = DeepTealPrimary,
)

@Composable
fun HowAreYouTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}