package com.amlan.rapidsplit

import android.app.Application
import com.amlan.rapidsplit.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class RapidSplitApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RapidSplitApp)
            modules(appModule)
        }
    }
}
