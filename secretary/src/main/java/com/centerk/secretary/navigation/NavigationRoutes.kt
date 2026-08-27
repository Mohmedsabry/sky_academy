package com.centerk.secretary.navigation

import kotlinx.serialization.Serializable

sealed interface NavigationRoutes

@Serializable
data object AuthGraph : NavigationRoutes

@Serializable
data object HomeGraph : NavigationRoutes
sealed interface AuthRoutes : NavigationRoutes {
    @Serializable
    data object SplashDest : AuthRoutes

    @Serializable
    data object LoginDest : AuthRoutes

    @Serializable
    data object ForgetYourPassword : AuthRoutes
}

@Serializable
sealed interface HomeRoutes : NavigationRoutes {
    @Serializable
    data object Home : HomeRoutes

    @Serializable
    data object Students : HomeRoutes

    @Serializable
    data object Groups : HomeRoutes

    @Serializable
    data object Finance : HomeRoutes

    @Serializable
    data object AddStudent : HomeRoutes
    @Serializable
    data class GroupDetails(
        val groupId: String
    ): HomeRoutes
}
