package com.centerk.secretary.finance.presentation

import com.centerk.secretary.navigation.NavigationRoutes

sealed interface FinanceEvents {
    data object UpdateShowValus : FinanceEvents
    data class NumberOfMonthsShown(val amount: Int) : FinanceEvents
}

sealed interface FinanceUiEvents : FinanceEvents {
    data class Navigate(
        val route: NavigationRoutes
    ) : FinanceUiEvents
}