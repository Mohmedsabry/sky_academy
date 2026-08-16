package com.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    secondary = onSecondaryDark,
    tertiary = trinaryColorDark,
    background = backgroundColorDark,
    surface = surfaceDark,
    surfaceContainerLowest = inputBordersDark
)

private val LightColorScheme = lightColorScheme(
    primary = primaryNight,
    secondary = onSecondaryNight,
    tertiary = trinaryColorNight,
    background = backgroundColorNight,
    surface = surfaceNight,
    surfaceContainerLowest = inputBordersNight
)

@Composable
fun CenteryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}