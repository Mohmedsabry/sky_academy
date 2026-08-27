package com.centerk.secretary.attendance.presentation

sealed interface AttendanceEvents {
    data class OnSelectMethod(
        val method: AttendanceMethod
    ) : AttendanceEvents

    data object OnClickQrScan : AttendanceEvents
    data object OnClickManaulSearch : AttendanceEvents
    data class OnSelectingGroup(
        val id: String
    ) : AttendanceEvents
}