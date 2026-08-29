package com.centerk.secretary.qr_scanner.presentation

import com.centerk.secretary.groups.domain.model.Group

data class QrState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val studentId: String = "",
    val group: Group = Group.empty()
)
