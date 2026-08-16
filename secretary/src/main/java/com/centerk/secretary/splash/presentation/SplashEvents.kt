package com.centerk.secretary.splash.presentation

sealed interface SplashEvents {
    data class SaveLanguage(val language: String) : SplashEvents
}