package com.centerk.secretary.common.di

import com.centerk.secretary.common.presentation.ConfigurationManager
import org.koin.dsl.module

val commonModule = module {
    single {
        ConfigurationManager(get())
    }
}