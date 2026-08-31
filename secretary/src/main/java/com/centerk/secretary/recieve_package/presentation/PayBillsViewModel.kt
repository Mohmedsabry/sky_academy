package com.centerk.secretary.recieve_package.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerk.secretary.student.domain.model.Student
import com.core.core_librarys.domain.util.PaymentStatues
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class PayBillsViewModel : ViewModel() {
    private val _state = MutableStateFlow(PayBillsState())
    val state = _state.asStateFlow()
    private val _uiEvents = Channel<PayBillsUiEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        val students = listOf(
            Student(
                "ياسمين محمود",
                UUID.randomUUID().toString(),
                "الصف العاشر • رياضيات A",
                null,
                PaymentStatues.Active
            ),
            Student(
                "عمر خالد عبد الرحمن",
                UUID.randomUUID().toString(),
                "الصف الحادي عشر • فيزياء",
                null,
                PaymentStatues.NotPayed
            ),
            Student(
                "كريم عادل مصطفى",
                UUID.randomUUID().toString(),
                "الصف العاشر • رياضيات B",
                null,
                PaymentStatues.Active
            ),
            Student(
                "نور الدين أحمد سليمان",
                UUID.randomUUID().toString(),
                "الصف العاشر • رياضيات A",
                null,
                PaymentStatues.Active
            ),
            Student(
                "مريم سامي جلال",
                UUID.randomUUID().toString(),
                "الصف التاسع • كيمياء",
                null,
                PaymentStatues.Suspended
            ),
        )
        _state.update { payBillsState ->
            payBillsState.copy(
                students = students,
                filteredStudents = students,
                billsDetails = students.associate {
                    when (it.paymentStatutes) {
                        PaymentStatues.Active -> it.studentId to 0L
                        PaymentStatues.NotPayed -> it.studentId to Random.nextLong(150, 450)
                        PaymentStatues.Suspended -> it.studentId to Random.nextLong(150, 450)
                        PaymentStatues.Filter -> it.studentId to Random.nextLong(150, 450)
                    }
                },
            )
        }
    }

    fun onEvent(events: PayBillsEvents) {
        when (events) {
            is PayBillsEvents.OnStudentClicked -> {
                viewModelScope.launch {
                    val student =
                        _state.value.students.firstOrNull { it.studentId == events.studentId }
                    if (student == null || student.paymentStatutes == PaymentStatues.Active) {
                        return@launch
                    }
                    _uiEvents.send(PayBillsUiEvents.NavigateToDetails(events.studentId))
                }
            }

            is PayBillsEvents.OnTyping -> {
                _state.update {
                    it.copy(query = events.query)
                }
                flowOf(_state.value.query)
                    .debounce(200.milliseconds)
                    .distinctUntilChanged()
                    .flatMapLatest { flowOf(it) }
                    .onEach { query ->
                        _state.update { payBillsState ->
                            payBillsState.copy(
                                filteredStudents = payBillsState.students.filter { student ->
                                    student.studentId.contains(query) || student.studentLevel.contains(
                                        query
                                    ) || student.name.contains(
                                        query
                                    ) || query.isEmpty()
                                }

                            )
                        }
                    }.launchIn(viewModelScope)
            }

            PayBillsUiEvents.NavigateUp -> {
                viewModelScope.launch {
                    _uiEvents.send(PayBillsUiEvents.NavigateUp)
                }
            }

            is PayBillsUiEvents.NavigateToDetails -> {
                viewModelScope.launch {
                    _uiEvents.send(PayBillsUiEvents.NavigateToDetails(events.studentId))
                }
            }
        }
    }
}