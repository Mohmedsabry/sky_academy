package com.centerk.secretary.login.presntation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerk.secretary.navigation.AuthRoutes
import com.centerk.secretary.navigation.HomeRoutes
import com.core.core_librarys.util.ContextExt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class LoginViewModel(
    private val contextExt: ContextExt,
    // will add validator and api authentication
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()
    private val _uiEvent = Channel<LoginUiEvents>(1)
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onEvent(event: LoginEvents) {
        when (event) {
            LoginEvents.OnLogin -> {
                viewModelScope.launch {
                    _state.update { state ->
                        state.copy(
                            isLoading = true
                        )
                    }
                    delay(2.seconds)
                    _state.update { state ->
                        state.copy(
                            isLoading = false
                        )
                    }
                    _uiEvent.send(LoginUiEvents.OnNavigation(HomeRoutes.Home))
                }
            }

            is LoginEvents.OnTypingEmail -> {
                _state.update { state ->
                    state.copy(
                        emailOrPhone = event.email
                    )
                }
            }

            is LoginEvents.OnTypingPassword -> {
                _state.update { state ->
                    state.copy(
                        password = event.password
                    )
                }
            }

            is LoginUiEvents.OnNavigation -> {
                viewModelScope.launch {
                    _uiEvent.send(LoginUiEvents.OnNavigation(event.dest))
                }
            }
        }
    }
}