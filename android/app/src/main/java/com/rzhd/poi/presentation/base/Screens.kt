package com.rzhd.poi.presentation.base

import androidx.fragment.app.Fragment
import com.rzhd.poi.presentation.auth.AuthFragment
import com.rzhd.poi.presentation.trip.create.CreateTripFragment
import com.rzhd.poi.presentation.trip.created.CreatedTripsFragment
import com.rzhd.poi.presentation.trip.info.RouteInfoFragment
import com.rzhd.poi.presentation.trip.poi.PoiFragment
import com.rzhd.poi.presentation.trip.station.info.StationInfoFragment
import com.rzhd.poi.presentation.trip.station.select.SelectStationFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AuthScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = AuthFragment()
}

object CreateTripScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = CreateTripFragment()
}


class SelectStationScreen(private val mode: Int, private val routeId: String) : SupportAppScreen() {

    companion object {

        const val MODE_DEPARTURE = 0
        const val MODE_ARRIVAL = 1
    }

    override fun getFragment(): Fragment = SelectStationFragment.getInstance(mode, routeId)
}

class RouteInfoScreen(private val routeId: String) : SupportAppScreen() {

    override fun getFragment(): Fragment = RouteInfoFragment.getInstance(routeId)
}

object CreatedTripsScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = CreatedTripsFragment()
}

class PoiScreen(private val poiId: String) : SupportAppScreen() {

    override fun getFragment(): Fragment = PoiFragment()
}

class StationInfoScreen(private val stationId: String) : SupportAppScreen() {

    override fun getFragment(): Fragment = StationInfoFragment()
}