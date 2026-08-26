package com.centerk.secretary.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.core_librarys.domain.util.ContextExt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SplashViewModel(
    private val contextExt: ContextExt
) : ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()
    private val _canMove = Channel<Boolean>(
        capacity = 1,
    )
    val canMove = _canMove.receiveAsFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    init {
        // processing configuration check
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoggedIn = contextExt.isLoggedIn(),
                )
            }
            delay(2.seconds)
            _canMove.send(true)
        }
    }

    fun onEvent(events: SplashEvents) {

    }
}