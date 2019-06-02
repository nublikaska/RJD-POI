package com.rzhd.poi.data.db

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.rzhd.poi.data.*
import com.rzhd.poi.data.prefs.SharedPrefs
import com.rzhd.poi.domain.currentUser
import com.rzhd.poi.domain.tripData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.koin.dsl.module

val dbModule = module {

    single { FirebaseFirestore.getInstance() }
    single<Repository> { RepositoryImpl(get(), get()) }
}

private class RepositoryImpl(
        private val firestore: FirebaseFirestore,
        private val sharePrefs: SharedPrefs
) : Repository {

    private val routesCache = mutableListOf<Route>()
    private val stationsCache = mutableMapOf<String, List<Station>>()
    private val stationDetailedCache = mutableMapOf<String, StationDetailed>()

    override suspend fun createUserTrip() = withContext(IO) {

        val tripData = sharePrefs.tripData ?: return@withContext
        val tripDataMap = mapOf(
                "routeId" to tripData.routeId,
                "departureId" to tripData.departureId,
                "arrivalId" to tripData.arrivalId,
                "departureDate" to tripData.departureTime
        )
        val task = firestore.collection("Users")
                .document(currentUser.uid)
                .collection("Trips")
                .document()
                .set(tripDataMap)

        Tasks.await(task)
        sharePrefs.tripData = null
    }

    override suspend fun getUserTrip(routeId: String): UserTrip = withContext(IO) {

        val task = firestore
                .collection("Users")
                .document(currentUser.uid)
                .collection("Trips")
                .whereEqualTo("routeId", routeId)
                .get()

        val snapshot = Tasks.await(task).first()
        UserTrip(snapshot.id, snapshot.data)
    }

    override suspend fun getUserTrips(): List<UserTrip> = withContext(IO) {

        val task = firestore.collection("Users").document(currentUser.uid).collection("Trips").get()
        Tasks.await(task).map { document -> UserTrip(document.id, document.data) }
    }

    override suspend fun getRoutes(): List<Route> = withContext(IO) {

        if (routesCache.isNotEmpty()) return@withContext routesCache
        val task = firestore.collection("Route").get()
        routesCache.apply { addAll(Tasks.await(task).map { document -> Route(document.id, document.data) }) }
    }

    override suspend fun getRoute(routeId: String): Route = withContext(IO) {

        routesCache.find { it.id == routeId } ?: run {

            val task = firestore.collection("Route").document(routeId).get()
            val docRef = Tasks.await(task)
            Route(docRef.id, docRef.data!!)
        }
    }

    override suspend fun getStationsForRoute(route: Route): List<Station> = withContext(IO) {

        if (stationsCache[route.id] != null) return@withContext stationsCache[route.id]!!
        val task = firestore.collection("Station").whereArrayContains("routes", route.id).get()
        stationsCache[route.id] = Tasks.await(task)
                .map { document -> StationFB(document.id, document.data).toStation(route.id) }
                .sortedBy { it.order }
        stationsCache[route.id]!!
    }


    override suspend fun getStationDetailed(station: Station): StationDetailed? = withContext(IO) {

        if (stationDetailedCache[station.id] != null) return@withContext stationDetailedCache[station.id]
        val task = firestore.collection("StationDetailed").document(station.detailId).get()
        val docRef = Tasks.await(task)
        docRef.data?.let { data -> stationDetailedCache[station.id] = StationDetailed(docRef.id, data) }
        stationDetailedCache[station.id]
    }
}