package com.rzhd.poi.data.db

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.rzhd.poi.data.Route
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class RepositoryImpl(private val firestore: FirebaseFirestore) : Repository {

    override suspend fun getRoutes(): List<Route> = withContext(IO) {

        val task = firestore.collection("Route").get()
        Tasks.await(task).map { document -> Route(document.id, document.data) }
    }
}