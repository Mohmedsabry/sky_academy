package com.centerk.secretary.finance.domain.model

import java.time.LocalDateTime

data class Transaction(
    val studentName: String,
    val paidMonth: String,
    val transactionDate: LocalDateTime,
    val amount: Long
)
