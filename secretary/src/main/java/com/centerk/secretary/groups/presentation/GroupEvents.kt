package com.centerk.secretary.groups.presentation

import com.centerk.secretary.navigation.NavigationRoutes

sealed interface GroupEvents {
    data class Navigate(val navigationRoutes: NavigationRoutes) : GroupEvents
}