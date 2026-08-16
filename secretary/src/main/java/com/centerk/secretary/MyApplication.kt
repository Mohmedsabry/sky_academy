package com.centerk.secretary

import android.app.Application
import com.centerk.secretary.login.di.loginModule
import com.centerk.secretary.splash.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(splashModule, loginModule)
        }
    }
}