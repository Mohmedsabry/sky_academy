package com.centerk.secretary

import android.app.Application
import com.centerk.secretary.common.di.appLevelModule
import com.centerk.secretary.finance.di.financeModule
import com.centerk.secretary.groups.di.groupModule
import com.centerk.secretary.home.di.homeModule
import com.centerk.secretary.login.di.loginModule
import com.centerk.secretary.splash.di.splashModule
import com.centerk.secretary.student.di.studentModule
import com.core.core_librarys.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                splashModule,
                loginModule,
                homeModule,
                appLevelModule,
                studentModule,
                commonModule,
                groupModule,
                financeModule
            )
        }
    }
}