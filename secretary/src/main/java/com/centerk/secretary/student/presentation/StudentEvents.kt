package com.centerk.secretary.student.presentation

import com.centerk.secretary.navigation.NavigationRoutes
import com.core.core_librarys.domain.util.PaymentStatues
import com.core.ui.util.UiMode

sealed interface StudentEvents {
    data class OnTyping(val query: String) : StudentEvents
    data class OnSelectingTags(val tage: String) : StudentEvents
    data class OnSelectingPayment(val paymentStatues: PaymentStatues?) : StudentEvents
    data class OnUiModeChange(val mode: UiMode) : StudentEvents
}

sealed interface StudentUiEvents : StudentEvents {
    data class OnNavigation(val navigationRoutes: NavigationRoutes) : StudentUiEvents
}