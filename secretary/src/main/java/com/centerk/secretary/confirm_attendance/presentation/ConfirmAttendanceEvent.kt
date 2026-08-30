package com.centerk.secretary.confirm_attendance.presentation

sealed interface ConfirmAttendanceUiEvent {
    data object NavigateUp: ConfirmAttendanceUiEvent
    data object NavigateToHome: ConfirmAttendanceUiEvent
}