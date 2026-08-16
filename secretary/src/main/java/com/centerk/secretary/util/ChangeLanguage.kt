package com.centerk.secretary.util

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.intl.Locale
import androidx.core.os.LocaleListCompat
import com.core.ui.theme.ChangeTypography
import com.core.ui.theme.getArabicTypography

@Composable
fun ChangeLanguage(
    locale: Locale,
    language: String?,
    onFinish: (String) -> Unit
) {
    val systemLanguage = locale.platformLocale.language
    val selectedLang = when {
        language != null -> language
        systemLanguage in listOf("ar", "en") -> systemLanguage
        else -> "ar"
    }
    val localList = LocaleListCompat.forLanguageTags(selectedLang)
    AppCompatDelegate.setApplicationLocales(localList)
    when (selectedLang) {
        "ar" -> {
            ChangeTypography(getArabicTypography())
        }

        "en" -> {
            ChangeTypography()
        }
    }
    Log.d("TAG", "system: $systemLanguage local: $language selected: $selectedLang")
    onFinish(selectedLang)
}