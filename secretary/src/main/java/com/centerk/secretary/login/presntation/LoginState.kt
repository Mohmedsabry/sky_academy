package com.centerk.secretary.login.presntation

data class LoginState(
    val isLoading: Boolean = false,
    val emailOrPhone: String = "",
    val password: String = "",
    val error: String? = null
)
