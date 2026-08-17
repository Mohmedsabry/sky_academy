package com.centerk.secretary.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerk.secretary.common.domain.Secretary
import com.centerk.secretary.home.domain.GroupInfo
import com.centerk.secretary.home.domain.Statics
import com.core.core_librarys.util.ContextExt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class HomeViewModel(
    private val contextExt: ContextExt
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    private val _uiEvent = Channel<HomeUiEvents>(1)
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val secretary = Secretary.empty().copy(
            name = "محمد صبرى",
            professionCode = "أ"
        )
        val group1 = GroupInfo(
            teacherName = "محمد أحمد",
            startTime = "4:00 م",
            endTime = "5:00 م",
            groupName = "حصه الرياضة أولى ثانوى",
            groupNumber = "مجموعه A"
        )
        val group2 = GroupInfo(
            teacherName = "على أحمد",
            startTime = "5:00 م",
            endTime = "6:00 م",
            groupName = "حصه إنجليزي أولى ثانوي",
            groupNumber = "مجموعه B"
        )
        val group3 = GroupInfo(
            teacherName = "محمود حسين",
            startTime = "7:00 م",
            endTime = "8:00 م",
            groupName = "حصه برمجة أولى ثانوي",
            groupNumber = "مجموعه A"
        )
        _state.update { state ->
            state.copy(
                secretary = secretary,
                statics = Statics(
                    totalGroups = 15,
                    totalStudentNumber = 127,
                    totalStudentHasBills = 12,
                    totalStudentPresentToday = 89,
                    groups = listOf(group1, group2, group3)
                ),
            )
        }
    }

    fun onEvent(event: HomeEvents) {
        when (event) {

            HomeEvents.PullToRefresh -> {
                _state.update {
                    it.copy(isLoading = true)
                }
                viewModelScope.launch {
                    delay(1.seconds)
                    _state.update {
                        it.copy(isLoading = false)
                    }
                }
            }

            is HomeUiEvents.Navigation -> {
                viewModelScope.launch {
                    _uiEvent.send(HomeUiEvents.Navigation(event.des))
                }
            }
        }
    }
}