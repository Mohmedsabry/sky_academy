package com.centerk.secretary.navigation

import kotlinx.serialization.Serializable
@Serializable
data object AuthGraph
@Serializable
data object HomeGraph
@Serializable
sealed interface AuthRoutes {
    @Serializable
    data object SplashDest : AuthRoutes

    @Serializable
    data object LoginDest : AuthRoutes
}

@Serializable
sealed interface HomeRoutes {
    @Serializable
    data object Home : HomeRoutes
}
