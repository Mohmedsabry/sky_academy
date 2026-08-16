package com.centerk.secretary.login.di

import com.centerk.secretary.login.presntation.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    viewModelOf(::LoginViewModel)
}