package com.centerk.secretary.payment_details.presentation

import com.centerk.secretary.navigation.NavigationRoutes
import com.centerk.secretary.payment_details.presentation.util.PaymentStrategy

sealed interface PaymentDetailsEvents {
    data object OnConfirmPayment : PaymentDetailsEvents
    data class OnChangeAmount(
        val amount: String
    ) : PaymentDetailsEvents

    data class OnChangePayment(
        val paymentStrategy: PaymentStrategy
    ) : PaymentDetailsEvents
}

sealed interface PaymentDetailsUiEvents : PaymentDetailsEvents {
    data object NavigateUp : PaymentDetailsUiEvents
    data class Toast(val massage: String) : PaymentDetailsUiEvents
    data class Navigate(val navigationRoutes: NavigationRoutes) : PaymentDetailsUiEvents
}