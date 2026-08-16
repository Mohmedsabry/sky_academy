package com.centerk.secretary.login.presntation

import com.centerk.secretary.navigation.NavigationRoutes

sealed interface LoginEvents {
    data class OnTypingEmail(val email: String) : LoginEvents
    data class OnTypingPassword(val password: String) : LoginEvents
    data object OnLogin : LoginEvents
}

sealed interface LoginUiEvents : LoginEvents {
    data class OnNavigation(val dest: NavigationRoutes) : LoginUiEvents
}