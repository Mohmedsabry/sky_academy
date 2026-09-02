package com.centerk.secretary.navigation

import kotlinx.serialization.SerialName
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
    ) : HomeRoutes

    @Serializable
    data object AttendanceScreen : HomeRoutes

    @Serializable
    data class QrScreen(val groupId: String) : HomeRoutes

    @Serializable
    data class ConfirmAttendance(
        @SerialName("student_id") val studentId: String,
        @SerialName("group_id") val groupId: String
    ) : HomeRoutes

    @Serializable
    data object ReceiveBills : HomeRoutes

    @Serializable
    data class PaymentDetails(
        @SerialName("student_id") val studentId: String,
        @SerialName("amount_should_paid") val amountShouldPaid: Long,
    ) : HomeRoutes
}
