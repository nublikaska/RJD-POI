package com.rzhd.poi.domain

import com.rzhd.poi.data.prefs.SharedPrefs

data class TripData(
    val routeId: String,
    val routeNumber: String,
    val departureId: String?,
    val departureName: String?,
    val arrivalId: String?,
    val arrivalName: String?,
    val departureTime: String?
) {

    val isValid: Boolean
        get() = routeId.isNotBlank() && departureId.orEmpty().isNotBlank()
                && arrivalId.orEmpty().isNotBlank() && departureTime.orEmpty().isNotBlank()
}

private const val ROUTE_ID_KEY = "route_id"
private const val ROUTE_NUMBER_KEY = "route_number"
private const val DEPARTURE_ID_KEY = "departure_id"
private const val DEPARTURE_NAME_KEY = "departure_name"
private const val ARRIVAL_ID_KEY = "arrival_id"
private const val ARRIVAL_NAME_KEY = "arrival_name"
private const val DEPARTURE_TIME_KEY = "departure_time"

var SharedPrefs.tripData: TripData?
    get() {
        val routeId = getString(ROUTE_ID_KEY) ?: return null
        val routeNumber = getString(ROUTE_NUMBER_KEY) ?: return null
        val departureId = getString(DEPARTURE_ID_KEY)
        val departureName = getString(DEPARTURE_NAME_KEY)
        val arrivalId = getString(ARRIVAL_ID_KEY)
        val arrivalName = getString(ARRIVAL_NAME_KEY)
        val departureTime = getString(DEPARTURE_TIME_KEY)
        return TripData(routeId, routeNumber, departureId, departureName, arrivalId, arrivalName, departureTime)
    }
    set(value) {
        if (value == null) {
            listOf(
                ROUTE_ID_KEY,
                ROUTE_NUMBER_KEY,
                DEPARTURE_TIME_KEY,
                DEPARTURE_NAME_KEY,
                DEPARTURE_ID_KEY,
                ARRIVAL_NAME_KEY,
                ARRIVAL_ID_KEY
            ).forEach(::remove)
            return
        }
        put(ROUTE_ID_KEY, value.routeId)
        put(ROUTE_NUMBER_KEY, value.routeNumber)
        value.departureId?.let { put(DEPARTURE_ID_KEY, it) } ?: remove(DEPARTURE_ID_KEY)
        value.departureName?.let { put(DEPARTURE_NAME_KEY, it) } ?: remove(DEPARTURE_NAME_KEY)
        value.arrivalId?.let { put(ARRIVAL_ID_KEY, it) } ?: remove(ARRIVAL_ID_KEY)
        value.arrivalName?.let { put(ARRIVAL_NAME_KEY, it) } ?: remove(ARRIVAL_NAME_KEY)
        value.departureTime?.let { put(DEPARTURE_TIME_KEY, it) } ?: remove(DEPARTURE_TIME_KEY)
    }
