package com.centerk.secretary.recieve_package.di

import com.centerk.secretary.recieve_package.presentation.PayBillsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val payBillsModule = module {
    viewModelOf(::PayBillsViewModel)
}