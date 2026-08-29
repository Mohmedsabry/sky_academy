package com.centerk.secretary.attendance.presentation

import com.centerk.secretary.navigation.NavigationRoutes

sealed interface AttendanceEvents {
    data class OnSelectMethod(
        val method: AttendanceMethod
    ) : AttendanceEvents

    data object OnClickQrScan : AttendanceEvents
    data object OnClickManaulSearch : AttendanceEvents
    data class OnSelectingGroup(
        val id: String
    ) : AttendanceEvents
    data class OnSelectingStudent(
        val id: String
    ) : AttendanceEvents
    data class OnQueryChange(
        val query: String
    ) : AttendanceEvents
}

sealed interface AttendanceUiEvents : AttendanceEvents {
    data object NavigateUp : AttendanceUiEvents
    data class ShowToast(val massage: String) : AttendanceUiEvents
    data object NavigateToQrScan : AttendanceUiEvents
}