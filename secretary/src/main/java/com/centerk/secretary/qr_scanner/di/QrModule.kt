package com.centerk.secretary.qr_scanner.di

import com.centerk.secretary.qr_scanner.presentation.QrViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val qrModule = module {
    viewModelOf(::QrViewModel)
}