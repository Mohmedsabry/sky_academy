package com.centerk.secretary.student.domain.model

import com.core.core_librarys.domain.util.PaymentStatues

data class Student(
    val name: String,
    val studentId: String,
    val studentLevel: String,
    val studentPic: String?,
    val paymentStatutes: PaymentStatues,
    val presentScore: Float = 0f
) {
    companion object {
        fun empty() = Student(
            name = "",
            studentId = "",
            studentLevel = "",
            studentPic = "",
            paymentStatutes = PaymentStatues.Active,
            presentScore = 0f
        )
    }
}
