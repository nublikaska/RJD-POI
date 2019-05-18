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