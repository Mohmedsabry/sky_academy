package com.centerk.secretary.common.presentation

import com.core.core_librarys.util.ContextExt
import com.core.ui.util.UiMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConfigurationManager(
    private val context: ContextExt
) {
    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val _language = MutableSharedFlow<String>()
    val language = _language
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000),
            context.getLanguage() ?: "ar"
        )
    private val _uiMode = MutableSharedFlow<String>()
    val uiMode = _uiMode.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5000),
        context.getUiMode() ?: UiMode.Night.mode
    )

    fun saveLanguage(language: String) {
        scope.launch {
            _language.emit(language)
            context.saveLanguage(language)
        }
    }

    fun saveUiMode(uiMode: String) {
        scope.launch {
            _uiMode.emit(uiMode)
            context.saveUiMode(uiMode)
        }
    }
}