package com.centerk.secretary.payment_details.presentation

import androidx.compose.material3.SnackbarHostState
import com.centerk.secretary.payment_details.presentation.util.PaymentStrategy
import com.centerk.secretary.student.domain.model.Student

data class PaymentDetailsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val student: Student = Student.empty(),
    val amountShouldPaid: Long = 0L,
    val receivedAmount: String = "",
    val paymentStrategy: PaymentStrategy = PaymentStrategy.Cash,
    val snackbarHostState: SnackbarHostState = SnackbarHostState()
)
