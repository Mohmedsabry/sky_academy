@file:OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)

package com.centerk.secretary.attendance.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerk.secretary.R
import com.centerk.secretary.attendance.presentation.AttendanceUiEvents.NavigateUp
import com.centerk.secretary.attendance.presentation.AttendanceUiEvents.ShowToast
import com.centerk.secretary.home.domain.GroupInfo
import com.centerk.secretary.student.domain.model.Student
import com.core.core_librarys.domain.util.ContextExt
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
import kotlin.time.Duration.Companion.milliseconds

class AttendanceViewModel(
    private val context: ContextExt
) : ViewModel() {
    private val _state = MutableStateFlow(AttendanceState())
    val state = _state.asStateFlow()
    private val _events = Channel<AttendanceUiEvents>()
    val events = _events.receiveAsFlow()

    init {
        val group1 = GroupInfo(
            teacherName = "محمد أحمد",
            startTime = "4:00 م",
            endTime = "5:00 م",
            groupName = "حصه الرياضة أولى ثانوى",
            groupLevel = "مجموعه A",
            groupId = "1",
            sessions = listOf()
        )
        val group2 = GroupInfo(
            teacherName = "على أحمد",
            startTime = "5:00 م",
            endTime = "6:00 م",
            groupName = "حصه إنجليزي أولى ثانوي",
            groupLevel = "مجموعه B",
            groupId = "2",
            sessions = listOf()
        )
        val group3 = GroupInfo(
            teacherName = "محمود حسين",
            startTime = "7:00 م",
            endTime = "8:00 م",
            groupName = "حصه برمجة أولى ثانوي",
            groupLevel = "مجموعه A",
            groupId = "3",
            sessions = listOf()
        )
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
        _state.update {
            it.copy(
                groups = listOf(group1, group2, group3),
                students = students,
            )
        }
    }

    fun onEvent(events: AttendanceEvents) {
        when (events) {
            AttendanceEvents.OnClickManaulSearch -> {
                viewModelScope.launch {
                    if (_state.value.selectedStudentId.isEmpty() || _state.value.selectedGroup.isEmpty()) {
                        _events.send(ShowToast(context.getString(R.string.pleas_select_group_and_student)))
                        return@launch
                    }
                    _events.send(AttendanceUiEvents.NavigateToConfirmAttendance)
                }
            }

            AttendanceEvents.OnClickQrScan -> {
                viewModelScope.launch {
                    _events.send(AttendanceUiEvents.NavigateToQrScan)
                }
            }

            is AttendanceEvents.OnQueryChange -> {
                _state.update {
                    it.copy(
                        query = events.query,
                        isLoadingStudents = true
                    )
                }
                flowOf(_state.value.query)
                    .debounce(200.milliseconds)
                    .distinctUntilChanged()
                    .flatMapLatest { flowOf(it) }
                    .onEach { query ->
                        _state.update { attendanceState ->
                            attendanceState.copy(
                                filteredStudents = attendanceState.students.filter { student ->
                                    student.studentId.contains(query) || student.name.contains(
                                        query
                                    ) || query.isEmpty() || student.studentLevel.contains(query)
                                },
                                isLoadingStudents = false
                            )
                        }
                    }
                    .launchIn(viewModelScope)
            }

            is AttendanceEvents.OnSelectMethod -> {
                when (events.method) {
                    AttendanceMethod.QR -> {
                        _state.update {
                            it.copy(
                                query = "",
                                showSearchBox = false,
                                attendanceMethod = events.method,
                            )
                        }
                    }

                    AttendanceMethod.Manual -> {
                        _state.update {
                            it.copy(
                                showSearchBox = true,
                                attendanceMethod = events.method,
                            )
                        }
                    }
                }
            }

            is AttendanceEvents.OnSelectingGroup -> {
                _state.update {
                    it.copy(
                        selectedGroup = events.id
                    )
                }
            }

            is AttendanceEvents.OnSelectingStudent -> {
                _state.update {
                    it.copy(
                        selectedStudentId = events.id
                    )
                }
            }

            NavigateUp -> {
                viewModelScope.launch {
                    _events.send(NavigateUp)
                }
            }

            is ShowToast -> {
                viewModelScope.launch {
                    _events.send(ShowToast(events.massage))
                }
            }

            AttendanceUiEvents.NavigateToQrScan -> {
                viewModelScope.launch {
                    _events.send(AttendanceUiEvents.NavigateToQrScan)
                }
            }

            AttendanceUiEvents.NavigateToConfirmAttendance -> {
                viewModelScope.launch {
                    _events.send(AttendanceUiEvents.NavigateToConfirmAttendance)
                }
            }
        }
    }
}