package com.centerk.secretary.util

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.text.intl.Locale
import androidx.core.os.LocaleListCompat


fun changeLanguage(
    locale: Locale,
    language: String,
    onFinish: (String) -> Unit
) {
    val systemLanguage = locale.platformLocale.language
    if (systemLanguage == language) return
    val localList = LocaleListCompat.forLanguageTags(language)
    AppCompatDelegate.setApplicationLocales(localList)
    onFinish(language)
}