package com.centerk.secretary.payment_details.di

import com.centerk.secretary.payment_details.presentation.PaymentDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val paymentDetailsModule = module {
    viewModelOf(::PaymentDetailsViewModel)
}