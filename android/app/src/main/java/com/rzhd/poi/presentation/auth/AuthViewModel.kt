package com.rzhd.poi.presentation.auth

import com.rzhd.poi.core.event.ViewEventStartAuth
import com.rzhd.poi.core.lifecycle.notNullLiveData
import com.rzhd.poi.core.vm.BaseViewModel
import com.rzhd.poi.data.db.Repository
import com.rzhd.poi.domain.isLoggedIn
import com.rzhd.poi.presentation.base.CreateTripScreen
import com.rzhd.poi.presentation.base.CreatedTripsScreen
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class AuthViewModel(
        private val router: Router,
        private val repository: Repository
) : BaseViewModel() {

    val needShowLoading by notNullLiveData(false)
    val needShowLoginButton by notNullLiveData(false)

    init {

        launch {
            when {
                isLoggedIn -> checkUserTrips()
                else -> needShowLoginButton.value = true
            }
        }
    }

    fun onLoginClick() = postViewEvents(ViewEventStartAuth)

    fun onAuthSuccess() {

        launch { checkUserTrips() }
    }

    private suspend fun checkUserTrips() {

        needShowLoading.value = true
        try {
            val userTrips = repository.getUserTrips()
            needShowLoading.value = false
            when {
                userTrips.isNotEmpty() -> router.newRootScreen(CreatedTripsScreen)
                else -> router.newRootScreen(CreateTripScreen)
            }
        } catch (e: Exception) {
            needShowLoading.value = false
            router.newRootScreen(CreateTripScreen)
        }
    }
}