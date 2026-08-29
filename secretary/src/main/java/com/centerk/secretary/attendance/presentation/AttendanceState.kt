package com.centerk.secretary.attendance.presentation

import androidx.compose.runtime.Immutable
import com.centerk.secretary.home.domain.GroupInfo
import com.centerk.secretary.student.domain.model.Student

@Immutable
data class AttendanceState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val isLoadingStudents: Boolean = false,
    val groups: List<GroupInfo> = listOf(),
    val attendanceMethod: AttendanceMethod = AttendanceMethod.QR,
    val selectedGroup: String = "",
    val query: String = "",
    val students: List<Student> = listOf(),
    val filteredStudents: List<Student> = listOf(),
    val showSearchBox: Boolean = false,
    val selectedStudentId: String = ""
)
