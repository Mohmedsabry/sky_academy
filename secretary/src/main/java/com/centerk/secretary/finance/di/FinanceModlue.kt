package com.centerk.secretary.finance.di

import com.centerk.secretary.finance.presentation.FinanceViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val financeModule = module {
    viewModelOf(::FinanceViewModel)
}