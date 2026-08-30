package com.centerk.secretary.qr_scanner.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.centerk.secretary.groups.domain.model.Group
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class QrViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(QrState())
    val state = _state.asStateFlow()
    private val _uiEvents = Channel<QrUiEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        val groupId = savedStateHandle["groupId"] ?: ""
        if (groupId == "123") {
            val group3 = Group(
                teacherName = "محمود حسين",
                time = "7:00 م",
                days = listOf(),
                students = listOf(),
                name = "حصه برمجة أولى ثانوي",
                level = "مجموعه A",
                groupId = "3",
                sessions = listOf()
            )
            _state.update {
                it.copy(
                    group = group3,
                )
            }
        }
    }

    fun onEvent(event: QrEvents) {
        when (event) {
            is QrEvents.OnScanQr -> {
                _state.update {
                    it.copy(
                        studentId = event.id,
                        isLoading = true
                    )
                }
                viewModelScope.launch {
                    _uiEvents.send(QrUiEvents.Toast("scanned : ${event.id}"))
                    delay(2.seconds)
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    delay(300.milliseconds)
                    _uiEvents.send(QrUiEvents.NavigateToMarkAttendance)
                }
            }

            QrUiEvents.NavigateToMarkAttendance -> {
                //navigate to mark attendance
            }

            QrUiEvents.NavigateUp -> {
                viewModelScope.launch {
                    _uiEvents.send(QrUiEvents.NavigateUp)
                }
            }

            is QrUiEvents.Toast -> {
                viewModelScope.launch {
                    _uiEvents.send(QrUiEvents.Toast(event.massage))
                }
            }
        }
    }
}