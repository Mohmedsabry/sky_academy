package com.centerk.secretary.qr_scanner.presentation

sealed interface QrEvents {
    data class OnScanQr(val id: String) : QrEvents
}

sealed interface QrUiEvents : QrEvents {
    data object NavigateUp : QrUiEvents
    data class Toast(val massage: String) : QrUiEvents
    data object NavigateToMarkAttendance : QrUiEvents
}