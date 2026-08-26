package com.centerk.secretary.finance.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerk.secretary.finance.domain.model.Transaction
import com.centerk.secretary.finance.presentation.FinanceUiEvents.Navigate
import com.centerk.secretary.finance.presentation.component.util.RevenueChartItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class FinanceViewModel : ViewModel() {
    private val _state = MutableStateFlow(FinanceState())
    val state = _state.asStateFlow()
    private val _uiEvents = Channel<FinanceEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        _state.update {
            it.copy(
                totalIncome = 45000,
                totalOutCome = 12500,
                statics = listOf(
                    RevenueChartItem("2026/1", 150000f),
                    RevenueChartItem("2026/2", 130000f),
                    RevenueChartItem("2026/3", 120000f),
                    RevenueChartItem("2026/4", 100000f),
                    RevenueChartItem("2026/5", 110000f),
                    RevenueChartItem("2026/6", 130000f),
                    RevenueChartItem("2026/7", 115000f),
                    RevenueChartItem("2026/8", 125000f),
                    RevenueChartItem("2026/9", 112000f),
                    RevenueChartItem("2026/10", 119000f),
                    RevenueChartItem("2026/11", 115000f),
                    RevenueChartItem("2026/12", 100000f),
                ),
                transactions = listOf(
                    Transaction(
                        studentName = "مريم سامي جلال",
                        paidMonth = "أكتوبر",
                        transactionDate = LocalDateTime.now(),
                        amount = 450
                    ),
                    Transaction(
                        studentName = "عمر خالد عبد الرحمن",
                        paidMonth = "أكتوبر",
                        transactionDate = LocalDateTime.now().minusDays(1),
                        amount = 450
                    ),
                    Transaction(
                        studentName = "كريم عادل مصطفى",
                        paidMonth = "أكتوبر",
                        transactionDate = LocalDateTime.of(
                            2026,
                            LocalDate.now().month.value,
                            LocalDate.now().dayOfMonth.minus(5),
                            15,
                            26
                        ),
                        amount = 450
                    )
                )
            )
        }
    }

    fun onEvent(events: FinanceEvents) {
        when (events) {
            is Navigate -> {
                viewModelScope.launch {
                    _uiEvents.send(Navigate(events.route))
                }
            }

            FinanceEvents.UpdateShowValus -> {
                _state.update {
                    it.copy(showValues = it.showValues.not())
                }
            }

            is FinanceEvents.NumberOfMonthsShown -> {
                _state.update {
                    it.copy(monthsCount = events.amount)
                }
            }
        }
    }
}