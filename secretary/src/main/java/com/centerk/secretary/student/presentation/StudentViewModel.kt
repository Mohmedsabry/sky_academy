@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.centerk.secretary.student.presentation

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerk.secretary.student.domain.model.Student
import com.core.core_librarys.domain.manager.QrGenerator
import com.core.core_librarys.domain.util.ContextExt
import com.core.core_librarys.domain.util.PaymentStatues
import com.core.ui.theme.primaryDark
import com.core.ui.theme.primaryNight
import com.core.ui.theme.surfaceDark
import com.core.ui.theme.surfaceNight
import com.core.ui.util.UiMode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.time.Duration.Companion.milliseconds

class StudentViewModel(
    private val contextExt: ContextExt,
    private val qrGenerator: QrGenerator
) : ViewModel() {
    private val _state = MutableStateFlow(StudentState())
    val state = _state.asStateFlow()
    private val _uiEvents = Channel<StudentUiEvents>(Channel.BUFFERED)
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
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    students = students,
                    filteredStudent = students,
                    suspendedStudent = students.filter { it.paymentStatutes == PaymentStatues.Suspended }.size,
                    activaStudent = students.filter { it.paymentStatutes == PaymentStatues.Active }.size,
                    unPaidStudent = students.filter { it.paymentStatutes == PaymentStatues.NotPayed }.size,
                    filterTags = students.map { it.studentLevel }.distinct(),
                )
            }
        }
    }

    fun onEvent(events: StudentEvents) {
        when (events) {
            is StudentEvents.OnSelectingPayment -> {
                _state.update { studentState ->
                    studentState.copy(
                        filteredStudent = studentState.students.filter { student ->
                            events.paymentStatues == student.paymentStatutes || events.paymentStatues == null
                        },
                        paymentStatues = events.paymentStatues,
                    )
                }
            }

            is StudentEvents.OnSelectingTags -> {
                _state.update { studentState ->
                    val mutableMap = studentState.selectedTags.toMutableMap()
                    mutableMap[events.tage] = mutableMap[events.tage] != true
                    studentState.copy(
                        selectedTags = mutableMap.toMap(),
                        filteredStudent = studentState.students.filter { student ->
                            (student.studentLevel in mutableMap && mutableMap[student.studentLevel] == true) || (mutableMap.all { !it.value })
                        },
                        paymentStatues = if (mutableMap.all { !it.value }) null else PaymentStatues.Filter
                    )
                }
            }

            is StudentEvents.OnTyping -> {
                flowOf(events.query)
                    .onEach {
                        _state.update { it.copy(query = events.query) }
                    }
                    .debounce(500.milliseconds)
                    .distinctUntilChanged()
                    .flatMapLatest { value ->
                        flow { emit(value) }
                    }.onEach { query ->
                        _state.update { state ->
                            state.copy(
                                filteredStudent = state.students.filter { student ->
                                    student.studentId.contains(query) || student.studentLevel.contains(
                                        query
                                    ) || student.name.contains(query) || query.isEmpty()
                                },
                            )
                        }
                    }.launchIn(viewModelScope)
            }

            is StudentUiEvents.OnNavigation -> {
                viewModelScope.launch {
                    _uiEvents.send(StudentUiEvents.OnNavigation(events.navigationRoutes))
                }
            }

            is StudentEvents.OnUiModeChange -> {
                val (bg, color) = when (events.mode) {
                    UiMode.Night -> {
                        surfaceNight.toArgb() to primaryNight.toArgb()
                    }

                    UiMode.Dark -> {
                        surfaceDark.toArgb() to primaryDark.toArgb()
                    }
                }
                viewModelScope.launch {
                    _state.update { state ->
                        state.copy(
                            qrCodes = state.students.associate {
                                it.studentId to qrGenerator.generateQr(it.studentId, bg, color)
                            }
                        )
                    }
                }
            }
        }
    }
}