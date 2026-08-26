package com.centerk.secretary.student.presentation

import android.graphics.Bitmap
import androidx.compose.runtime.Immutable
import com.centerk.secretary.student.domain.model.Student
import com.core.core_librarys.domain.util.PaymentStatues

@Immutable
data class StudentState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val students: List<Student> = listOf(),
    val filteredStudent: List<Student> = listOf(),
    val query: String = "",
    val filterTags: List<String> = listOf(),
    val paymentStatues: PaymentStatues? = null,
    val suspendedStudent: Int = 0,
    val unPaidStudent: Int = 0,
    val activaStudent: Int = 0,
    val qrCodes: Map<String, Bitmap> = mapOf(),
    val selectedTags: Map<String, Boolean> = mapOf()
)
