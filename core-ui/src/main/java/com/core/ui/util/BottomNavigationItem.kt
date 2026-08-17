package com.core.ui.util

data class BottomNavigationItem(
    val title: String,
    val icon: Int,
    val navigationRoute: NavigationRoute
)

enum class NavigationRoute {
    Home,
    Student,
    Groups,
    Finance
}
