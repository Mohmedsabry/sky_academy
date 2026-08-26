package com.core.core_librarys.di

import com.core.core_librarys.data.manager.QrGeneratorImp
import com.core.core_librarys.domain.manager.QrGenerator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    singleOf(::QrGeneratorImp).bind<QrGenerator>()
}