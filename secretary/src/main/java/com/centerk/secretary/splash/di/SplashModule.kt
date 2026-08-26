package com.centerk.secretary.splash.di

import com.centerk.secretary.splash.presentation.SplashViewModel
import com.centerk.secretary.util.ContextExtImp
import com.core.core_librarys.domain.util.ContextExt
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val splashModule = module {
    single<ContextExt> {
        ContextExtImp(get())
    }
    viewModelOf(::SplashViewModel)
}