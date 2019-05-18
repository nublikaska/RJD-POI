package com.rzhd.poi

import android.app.Application
import com.rzhd.poi.presentation.auth.di.authModule
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
                authModule
            )
        }
    }
}