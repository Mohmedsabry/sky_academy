package com.centerk.secretary.group_details.presntation

import com.centerk.secretary.navigation.NavigationRoutes

sealed interface GroupDetailsEvents {
    data class Navigate(val navigationRoutes: NavigationRoutes) : GroupDetailsEvents
}