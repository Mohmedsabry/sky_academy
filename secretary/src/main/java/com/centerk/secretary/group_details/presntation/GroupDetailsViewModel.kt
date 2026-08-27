package com.centerk.secretary.group_details.presntation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerk.secretary.groups.domain.model.Group
import com.centerk.secretary.groups.domain.model.Session
import com.centerk.secretary.student.domain.model.Student
import com.centerk.secretary.util.getStartAndEndTimeFormat
import com.core.core_librarys.domain.util.PaymentStatues
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class GroupDetailsViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(GroupDetailsState())
    val state = _state.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), GroupDetailsState()
    )
    private val _uiEvents = Channel<GroupDetailsEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        val students = listOf(
            Student(
                "ياسمين محمود",
                UUID.randomUUID().toString(),
                "الصف العاشر • رياضيات A",
                null,
                PaymentStatues.Active,
                presentScore = 8f
            ),
            Student(
                "عمر خالد عبد الرحمن",
                UUID.randomUUID().toString(),
                "الصف الحادي عشر • فيزياء",
                null,
                PaymentStatues.NotPayed,
                presentScore = 6f
            ),
            Student(
                "كريم عادل مصطفى",
                UUID.randomUUID().toString(),
                "الصف العاشر • رياضيات B",
                null,
                PaymentStatues.Active,
                presentScore = 5f
            ),
            Student(
                "نور الدين أحمد سليمان",
                UUID.randomUUID().toString(),
                "الصف العاشر • رياضيات A",
                null,
                PaymentStatues.Active,
                presentScore = 8f
            ),
            Student(
                "مريم سامي جلال",
                UUID.randomUUID().toString(),
                "الصف التاسع • كيمياء",
                null,
                PaymentStatues.Suspended,
                presentScore = 9f
            ),
        )
        val session = listOf(
            Session(
                name = "حساب المثلثات",
                sessionNumber = "الدرس الرابع",
                sessionStartDate = LocalDateTime.now().plusHours(1),
                sessionEndDate = LocalDateTime.now().plusHours(2)
            ),
            Session.empty(),
            Session.empty(),
            Session.empty(),
            Session.empty(),
            Session.empty(),
            Session.empty(),
            Session.empty(),
            Session.empty(),
        )
        val group = Group(
            name = "مجموعة الرياضيات - ثانوي",
            level = "المرحلة الثانوية • الصف العاشر (مجموعة A)",
            teacherName = "أحمد سالم",
            time = LocalDateTime.now()
                .getStartAndEndTimeFormat(LocalDateTime.now().plusHours(1)),
            days = listOf("السبت", "الثلاثاء"),
            students = students,
            sessions = session,
            groupId = "group:1"
        )
        _state.update {
            it.copy(
                group = group,
            )
        }
    }

    fun onEvent(events: GroupDetailsEvents) {
        when (events) {
            is GroupDetailsEvents.Navigate -> {
                viewModelScope.launch {
                    _uiEvents.send(GroupDetailsEvents.Navigate(events.navigationRoutes))
                }
            }
        }
    }
}