package com.centerk.secretary.common.di

import com.centerk.secretary.common.presentation.ConfigurationManager
import org.koin.dsl.module

val appLevelModule = module {
    single {
        ConfigurationManager(get())
    }
}