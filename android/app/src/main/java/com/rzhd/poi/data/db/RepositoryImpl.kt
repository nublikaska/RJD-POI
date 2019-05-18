package com.rzhd.poi.data.db

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.rzhd.poi.data.Route
import com.rzhd.poi.data.Station
import com.rzhd.poi.data.StationDetailed
import com.rzhd.poi.data.UserTrip
import com.rzhd.poi.domain.currentUser
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class RepositoryImpl(private val firestore: FirebaseFirestore) : Repository {

    override suspend fun createUserTrip(routeId: String, departureDate: String) = withContext(IO) {

        val task = firestore.collection("Users")
            .document(currentUser.uid)
            .collection("Trips")
            .document()
            .set(mapOf("routeId" to routeId, "departureDate" to departureDate))

        Tasks.await(task)
        Unit
    }

    override suspend fun getUserTrips(): List<UserTrip> = withContext(IO) {

        val task = firestore.collection("Users").document(currentUser.uid).collection("Trips").get()
        Tasks.await(task).map { document -> UserTrip(document.id, document.data) }
    }

    override suspend fun getRoutes(): List<Route> = withContext(IO) {

        val task = firestore.collection("Route").get()
        Tasks.await(task).map { document -> Route(document.id, document.data) }
    }

    override suspend fun getStationsForRoute(route: Route): List<Station> = withContext(IO) {

        route.stopsIds.mapNotNull { stopId ->
            val task = firestore.collection("Station").document(stopId).get()
            val docRef = Tasks.await(task)
            docRef.data?.let { data -> Station(docRef.id, data) }
        }
    }

    override suspend fun getStationDetailed(station: Station): StationDetailed? = withContext(IO) {

        val task = firestore.collection("StationDetailed").document(station.detailId).get()
        val docRef = Tasks.await(task)
        docRef.data?.let { data -> StationDetailed(docRef.id, data) }
    }
}