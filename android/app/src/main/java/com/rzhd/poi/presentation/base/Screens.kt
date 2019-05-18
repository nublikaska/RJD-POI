package com.rzhd.poi.presentation.base

import androidx.fragment.app.Fragment
import com.rzhd.poi.presentation.auth.AuthFragment
import com.rzhd.poi.presentation.trip.create.CreateTripFragment
import com.rzhd.poi.presentation.trip.info.RouteInfoFragment
import com.rzhd.poi.presentation.trip.station.SelectStationFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class AuthScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = AuthFragment()
}

class CreateTripScreen : SupportAppScreen() {

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