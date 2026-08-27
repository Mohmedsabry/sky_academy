package com.centerk.secretary.group_details.di

import com.centerk.secretary.group_details.presntation.GroupDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val groupDetailsModule = module {
    viewModelOf(::GroupDetailsViewModel)
}