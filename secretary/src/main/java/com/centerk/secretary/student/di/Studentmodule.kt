package com.centerk.secretary.student.di

import com.centerk.secretary.student.presentation.StudentViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val studentModule = module {
    viewModelOf(::StudentViewModel)
}