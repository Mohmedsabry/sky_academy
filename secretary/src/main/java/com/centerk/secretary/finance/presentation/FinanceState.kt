package com.centerk.secretary.finance.presentation

import androidx.compose.runtime.Immutable
import com.centerk.secretary.finance.domain.model.Transaction
import com.centerk.secretary.finance.presentation.component.util.RevenueChartItem

@Immutable
data class FinanceState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val totalOutCome: Long = 0,
    val totalIncome: Long = 0,
    val statics: List<RevenueChartItem> = listOf(),
    val transactions: List<Transaction> = listOf(),
    val showValues: Boolean = true,
    val monthsCount: Int = 6
)
