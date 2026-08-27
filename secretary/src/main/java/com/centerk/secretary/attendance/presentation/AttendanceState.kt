package com.centerk.secretary.attendance.presentation

import androidx.compose.runtime.Immutable
import com.centerk.secretary.home.domain.GroupInfo

@Immutable
data class AttendanceState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val groups: List<GroupInfo> = listOf(),
    val attendanceMethod: AttendanceMethod = AttendanceMethod.QR,
    val selectedGroup: String = ""
)
