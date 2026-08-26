package com.centerk.secretary.common.presentation.util

import com.centerk.secretary.navigation.HomeRoutes
import com.centerk.secretary.navigation.NavigationRoutes

fun handleBottomNavigation(
    currentItem: NavigationRoute,
    clickedItem: NavigationRoute
): NavigationRoutes? {
    if (currentItem == clickedItem) return null
    return when (clickedItem) {
        NavigationRoute.Home -> HomeRoutes.Home
        NavigationRoute.Student -> HomeRoutes.Students
        NavigationRoute.Groups -> HomeRoutes.Groups
        NavigationRoute.Finance -> HomeRoutes.Finance
    }
}