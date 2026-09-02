package com.centerk.secretary.recieve_package.presentation

import androidx.compose.runtime.Immutable
import com.centerk.secretary.student.domain.model.Student

@Immutable
data class PayBillsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val students: List<Student> = listOf(),
    val filteredStudents: List<Student> = listOf(),
    val billsDetails: Map<String, Long> = mapOf(),
    val query: String = ""
)
