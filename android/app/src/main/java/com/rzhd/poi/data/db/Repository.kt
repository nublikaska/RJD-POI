package com.rzhd.poi.data.db

import com.rzhd.poi.data.Route

interface Repository {

    suspend fun getRoutes(): List<Route>
}