package com.rzhd.poi

import android.app.Application
import com.rzhd.poi.data.db.dbModule
import com.rzhd.poi.data.prefs.sharedPrefsModule
import com.rzhd.poi.presentation.auth.authModule
import com.rzhd.poi.presentation.base.navigationModule
import com.rzhd.poi.presentation.trip.create.createTripModule
import com.rzhd.poi.presentation.trip.created.createdTripsModule
import com.rzhd.poi.presentation.trip.info.routeInfoModule
import com.rzhd.poi.presentation.trip.station.selectStationModule
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
                createTripModule,
                selectStationModule,
                routeInfoModule,
                createdTripsModule,

                navigationModule,

                dbModule,

                sharedPrefsModule
            )
        }
    }
}