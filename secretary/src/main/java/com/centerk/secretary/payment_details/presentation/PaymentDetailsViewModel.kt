package com.centerk.secretary.payment_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerk.secretary.R
import com.centerk.secretary.navigation.HomeRoutes
import com.centerk.secretary.payment_details.presentation.PaymentDetailsUiEvents.Navigate
import com.centerk.secretary.payment_details.presentation.PaymentDetailsUiEvents.NavigateUp
import com.centerk.secretary.payment_details.presentation.PaymentDetailsUiEvents.Toast
import com.centerk.secretary.student.domain.model.Student
import com.core.core_librarys.domain.util.ContextExt
import com.core.core_librarys.domain.util.PaymentStatues
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.toLongOrDefault
import kotlin.time.Duration.Companion.milliseconds

class PaymentDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val contextExt: ContextExt
) : ViewModel() {
    private val _state = MutableStateFlow(PaymentDetailsState())
    val state = _state.asStateFlow()
    private val _uiEvent = Channel<PaymentDetailsUiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val studentId = savedStateHandle["student_id"] ?: ""
        val amountShouldPaid = savedStateHandle["amount_should_paid"] ?: 0L
        val student = Student(
            "ياسمين محمود",
            studentId,
            "الصف العاشر • رياضيات A",
            null,
            PaymentStatues.Active
        )
        _state.update {
            it.copy(
                student = student,
                amountShouldPaid = amountShouldPaid,
            )
        }
    }

    fun onEvent(events: PaymentDetailsEvents) {
        when (events) {
            is PaymentDetailsEvents.OnChangeAmount -> {
                _state.update {
                    it.copy(
                        receivedAmount = events.amount
                    )
                }
            }

            is PaymentDetailsEvents.OnChangePayment -> {
                _state.update {
                    it.copy(
                        paymentStrategy = events.paymentStrategy
                    )
                }
            }

            PaymentDetailsEvents.OnConfirmPayment -> {
                viewModelScope.launch {
                    val amount = state.value.receivedAmount.toLongOrDefault(0)
                    if (amount == 0L || amount > _state.value.amountShouldPaid) {
                        _uiEvent.send(Toast(contextExt.getString(R.string.payment_unseffesion)))
                        return@launch
                    }
                    _uiEvent.send(Toast(contextExt.getString(R.string.payment_successfully_added)))
                    delay(1000.milliseconds)
                    _uiEvent.send(Navigate(HomeRoutes.Home))
                }
            }

            NavigateUp -> {
                viewModelScope.launch {
                    _uiEvent.send(NavigateUp)
                }
            }

            is Toast -> {
                viewModelScope.launch {
                    _uiEvent.send(Toast(events.massage))
                }
            }

            is Navigate -> {
                viewModelScope.launch {
                    _uiEvent.send(Navigate(events.navigationRoutes))
                }
            }
        }
    }
}