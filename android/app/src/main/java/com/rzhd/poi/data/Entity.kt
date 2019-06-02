package com.rzhd.poi.data

class Route(val id: String, map: Map<String, Any>) {

    val arrival: String by map
    val arrivalPlace: String by map
    val departure: String by map
    val departurePlace: String by map
    val number: String by map
    val stopsIds: List<String> by map
    val travelTime: Int by map
}

class StationFB(val id: String, map: Map<String, Any>) {

    val areaName: String by map
    val arrivalTime: Map<String, String> by map
    val detailId: String by map
    val order: Map<String, Int> by map
    val stopTime: Map<String, Int> by map
    val stopName: String by map
    val routes: List<String> by map

    fun toStation(routeId: String): Station = Station(
        id,
        areaName,
        arrivalTime.getValue(routeId),
        detailId,
        order.getValue(routeId),
        stopTime.getValue(routeId),
        stopName
    )
}

data class Station(
    val id: String,
    val areaName: String,
    val arrivalTime: String,
    val detailId: String,
    val order: Int,
    val stopTime: Int,
    val stopName: String
)

class StationDetailed(val id: String, map: Map<String, Any?>) {

    val detailInfo: String by map
    val name: String by map
    val poiIds: List<String>? by map
}

class UserTrip(val id: String, map: Map<String, Any?>) {

    val routeId: String by map
    val departureDate: String by map
    val departureId: String by map
    val arrivalId: String by map
}

class Poi(val id: String, map: Map<String, Any?>) {

    private val _category: String by map
    val areaName: String by map
    val title: String? by map
    val name: String? by map
    val detailInfo: String by map

    val category: PoiCategory? = PoiCategory.values().find { it.stringValue == _category }
}

enum class PoiCategory(val stringValue: String) {

    PEOPLE("people"),
    ARCHITECTURE("architecture"),
    MUSEUMS("museums"),
    TEMPLE("temple"),
    PARKS("parks"),
    LAKES("lakes"),
    RESERVES("reserves"),
    MONUMENTS("monuments"),
    BATTLE("battle"),
    COOL("cool")
}