package com.centerk.secretary.groups.di

import com.centerk.secretary.groups.presentation.GroupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val groupModule = module {
    viewModelOf(::GroupViewModel)
}