package com.centerk.secretary.util

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.text.intl.Locale
import androidx.core.os.LocaleListCompat

fun changeLanguage(
    locale: Locale,
    language: String?,
) {
    val systemLanguage = locale.platformLocale.language
    val selectedLang = when {
        language != null -> language
        systemLanguage in listOf("ar", "en") -> systemLanguage
        else -> "ar"
    }
    val localList = LocaleListCompat.forLanguageTags(selectedLang)
    AppCompatDelegate.setApplicationLocales(localList)
}