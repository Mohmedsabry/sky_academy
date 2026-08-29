package com.centerk.secretary.attendance.di

import com.centerk.secretary.attendance.presentation.AttendanceViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val attendanceModule = module {
    viewModelOf(::AttendanceViewModel)
}