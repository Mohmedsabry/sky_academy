package com.centerk.secretary.home.presentation

import com.centerk.secretary.navigation.NavigationRoutes

sealed interface HomeEvents {
    data object PullToRefresh : HomeEvents
}

sealed interface HomeUiEvents: HomeEvents {
    data class Navigation(val des: NavigationRoutes) : HomeUiEvents
}