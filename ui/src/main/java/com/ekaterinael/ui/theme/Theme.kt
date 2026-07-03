package com.ekaterinael.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    background = Color.Black,
    onSurface = Color.White,
    primary = Purple80,
    primaryContainer = Color(0xFF1d2123),
    onPrimaryContainer = Color.White,
    secondary = Red,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    background = Color(0xFFFAF9F6),
    onBackground = Color(0xFF4D4D53),
    surfaceContainer = Color(0xFFFBFAF6),
    onSurface = Color(0xFF4D4D53),
    secondaryContainer = Color(0xFFFCFBF9),
    primary = Color(0xFF135862),
    secondary = PurpleGrey40,
    tertiary = Pink40
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