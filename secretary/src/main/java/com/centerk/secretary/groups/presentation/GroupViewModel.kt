package com.centerk.secretary.groups.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerk.secretary.groups.domain.model.Group
import com.centerk.secretary.util.getStartAndEndTimeFormat
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class GroupViewModel : ViewModel() {
    private val _state = MutableStateFlow(GroupState())
    val state = _state.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), GroupState()
    )
    private val _uiEvents = Channel<GroupEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        val group = listOf(
            Group(
                name = "مجموعة الرياضيات - ثانوي",
                level = "المرحلة الثانوية • الصف العاشر (مجموعة A)",
                teacherName = "أحمد سالم",
                time = LocalDateTime.now()
                    .getStartAndEndTimeFormat(LocalDateTime.now().plusHours(1)),
                days = listOf("السبت", "الثلاثاء"),
                students = listOf(),
                sessions = listOf(),
                groupId = "group:1"
            ),
            Group(
                name = "مجموعة الفيزياء - ثانوي",
                level = "المرحلة الثانوية • الصف العاشر (مجموعة A)",
                teacherName = "محمد على",
                time = LocalDateTime.now()
                    .getStartAndEndTimeFormat(LocalDateTime.now().plusHours(1)),
                days = listOf("الأحد", "الخميس"),
                students = listOf(),
                sessions = listOf(),
                groupId = "group:2"
            ),
            Group(
                name = "مجموعة الرياضيات - إعدادي",
                level = "المرحلة الإعدادية • الصف السابع (مجموعة B)",
                teacherName = "أحمد خالد",
                time = LocalDateTime.now()
                    .getStartAndEndTimeFormat(LocalDateTime.now().plusHours(1)),
                days = listOf("الخميس", "الإثنين"),
                students = listOf(),
                sessions = listOf(),
                groupId = "group:3"
            ),
            Group(
                name = "مجموعة الرياضيات - إعدادي",
                level = "المرحلة الإعدادية • الصف السابع (مجموعة A)",
                teacherName = "محمد رجب",
                time = LocalDateTime.now()
                    .getStartAndEndTimeFormat(LocalDateTime.now().plusHours(1)),
                days = listOf("السبت", "الثلاثاء"),
                students = listOf(),
                sessions = listOf(),
                groupId = "group:4"
            ),
            Group(
                name = "مجموعة الرياضيات - ثانوي",
                level = "المرحلة الثانوية • الصف العاشر (مجموعة A)",
                teacherName = "محمد خليل",
                time = LocalDateTime.now()
                    .getStartAndEndTimeFormat(LocalDateTime.now().plusHours(1)),
                days = listOf("السبت", "الثلاثاء"),
                students = listOf(),
                sessions = listOf(),
                groupId = "group:5"
            )
        )
        _state.update {
            it.copy(
                groups = group,
            )
        }
    }

    fun onEvent(events: GroupEvents) {
        when (events) {
            is GroupEvents.Navigate -> {
                viewModelScope.launch {
                    _uiEvents.send(GroupEvents.Navigate(events.navigationRoutes))
                }
            }
        }
    }
}