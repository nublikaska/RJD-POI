package com.rzhd.poi

import android.app.Application
import com.rzhd.poi.data.db.dbModule
import com.rzhd.poi.presentation.auth.authModule
import com.rzhd.poi.presentation.base.navigationModule
import com.rzhd.poi.presentation.main.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@App)
            androidLogger()
            modules(
                authModule,
                mainModule,

                navigationModule,

                dbModule
            )
        }
    }
}