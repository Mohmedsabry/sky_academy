package com.centerk.secretary.confirm_attendance.di

import com.centerk.secretary.confirm_attendance.presentation.ConfirmAttendanceViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val confirmAttendanceModule = module {
    viewModelOf(::ConfirmAttendanceViewModel)
}