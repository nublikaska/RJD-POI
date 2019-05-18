package com.rzhd.poi.data.db

import com.rzhd.poi.data.Route
import com.rzhd.poi.data.Station
import com.rzhd.poi.data.StationDetailed
import com.rzhd.poi.data.UserTrip

interface Repository {

    suspend fun createUserTrip(routeId: String, departureDate: String)
    suspend fun getUserTrips(): List<UserTrip>
    suspend fun getRoutes(): List<Route>
    suspend fun getStationsForRoute(route: Route): List<Station>
    suspend fun getStationDetailed(station: Station): StationDetailed?
}