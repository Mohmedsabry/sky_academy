package com.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    secondary = onSecondaryDark,
    tertiary = trinaryColorDark,
    background = backgroundColorDark,
    onBackground = backgroundColorNight,
    surface = surfaceDark,
    surfaceContainerLowest = inputBordersDark,
    errorContainer = errorBackGroundDark,
    error = errorColorDark,
)

private val LightColorScheme = lightColorScheme(
    primary = primaryNight,
    secondary = onSecondaryNight,
    tertiary = trinaryColorNight,
    background = backgroundColorNight,
    onBackground = backgroundColorDark,
    surface = surfaceNight,
    surfaceContainerLowest = inputBordersNight,
    errorContainer = errorBackGroundLight,
    error = errorColorLight
)

@Composable
fun CenteryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    selectedLang: String = "ar",
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> {
            OrangeText = Color(0xFFE8B84B)
            OrangeBg = Color(0xFF332A11)
            successBg = Color(0xFF16332C)
            successColor = Color(0xFF5BC9A8)
            selectedColor = Color(0xFF1B1E17)
            qrBackground = Color(0xFF2E3227)
            groupInQrBG = Color(0xFF1B1E17)
            blackAndWhite = Color(0xFF121410)
            DarkColorScheme
        }

        else -> {
            OrangeText = Color(0xFFD97706)
            OrangeBg = Color(0xFFFEF3C7)
            successBg = Color(0xFFCCFBF1)
            successColor = Color(0xFF0D9488)
            selectedColor = Color(0xFFF1F5FE)
            qrBackground = Color(0xFF20240F)
            blackAndWhite = Color(0xFFFFFFFF)
            groupInQrBG = Color(0xFFFFFFFF).copy(alpha = 12.16f)
            LightColorScheme
        }
    }
    when (selectedLang) {
        "ar" -> {
            newTypography = getArabicTypography()
        }

        "en" -> {
            newTypography = getEnglishFrenchTypography()
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = newTypography,
        content = content
    )
}