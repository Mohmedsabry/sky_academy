package com.centerk.secretary.confirm_attendance.presentation

import com.centerk.secretary.groups.domain.model.Group
import com.centerk.secretary.student.domain.model.Student

data class ConfirmAttendanceState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val student: Student = Student.empty(),
    val group: Group = Group.empty()
)
