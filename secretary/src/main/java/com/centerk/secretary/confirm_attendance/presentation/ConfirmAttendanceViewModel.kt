package com.centerk.secretary.confirm_attendance.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerk.secretary.groups.domain.model.Group
import com.centerk.secretary.student.domain.model.Student
import com.core.core_librarys.domain.util.PaymentStatues
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConfirmAttendanceViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(ConfirmAttendanceState())
    val state = _state.asStateFlow()
    private val _channel = Channel<ConfirmAttendanceUiEvent>()
    val channel = _channel.receiveAsFlow()

    init {
        val studentId = savedStateHandle["student_id"] ?: ""
        val groupId = savedStateHandle["group_id"] ?: ""
        val student = Student(
            "ياسمين محمود",
            studentId,
            "الصف العاشر • رياضيات A",
            null,
            PaymentStatues.Active
        )
        val group = Group(
            teacherName = "محمود حسين",
            time = "7:00 م",
            name = "حصه برمجة أولى ثانوي",
            level = "مجموعه A",
            groupId = groupId,
            sessions = listOf(),
            students = listOf(),
            days = listOf()
        )
        _state.update {
            it.copy(
                student = student,
                group = group,
            )
        }
    }

    fun onEvent(event: ConfirmAttendanceUiEvent) {
        viewModelScope.launch {
            _channel.send(event)
        }
    }
}