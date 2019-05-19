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

class Station(val id: String, map: Map<String, Any>) {

    val areaName: String by map
    val arrivalTime: String by map
    val detailId: String by map
    val order: Int by map
    val stopTime: Int by map
    val stopsName: String by map
}

class StationDetailed(val id: String, map: Map<String, Any?>) {

    val detailInfo: String by map
    val poiIds: List<String>? by map
}

class UserTrip(val id: String, map: Map<String, Any?>) {

    val routeId: String by map
    val departureDate: String by map
    val departureId: String by map
    val arrivalId: String by map
}