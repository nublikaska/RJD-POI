package com.rzhd.poi.presentation.trip.create

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.rzhd.poi.core.event.ViewEventShowDatePicker
import com.rzhd.poi.core.event.ViewEventShowToast
import com.rzhd.poi.core.lifecycle.notNullLiveData
import com.rzhd.poi.core.vm.BaseViewModel
import com.rzhd.poi.data.db.Repository
import com.rzhd.poi.data.prefs.SharedPrefs
import com.rzhd.poi.domain.TripData
import com.rzhd.poi.domain.tripData
import com.rzhd.poi.presentation.base.SelectStationScreen
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class CreateTripViewModel(
    private val router: Router,
    private val repository: Repository,
    private val sharedPrefs: SharedPrefs
) : BaseViewModel() {

    val isButtonEnabled by notNullLiveData(false)
    val needShowFields by notNullLiveData(false)
    val needShowLoading by notNullLiveData(false)

    val routeCode by notNullLiveData("", ::findRouteByCode)
    val departureName by notNullLiveData("") { checkButtonEnabled() }
    val arrivalName by notNullLiveData("") { checkButtonEnabled() }
    val departureTime by notNullLiveData("") { checkButtonEnabled() }

    override val exceptionHandler = CoroutineExceptionHandler { _, throwable ->

        throwable.message?.let { postViewEvents(ViewEventShowToast(it)) }
    }

    private var searchJob: Job? = null
    private var currentTripData: TripData? = null

    init {

        launch {

            needShowLoading.value = true
            val routes = repository.getRoutes()
            needShowLoading.value = false
            needShowFields.value = true

            val savedTripData = sharedPrefs.tripData
            savedTripData?.let { tripData ->

                currentTripData = tripData
                routeCode.value = routes.find { it.id == savedTripData.routeId }!!.number
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun checkStations() {

        isButtonEnabled.value = departureTime.value.isNotBlank() &&
                arrivalName.value.isNotBlank() &&
                departureName.value.isNotBlank() &&
                routeCode.value.isNotBlank() && sharedPrefs.tripData?.isValid == true

        launch {
            val savedTripData = sharedPrefs.tripData
            savedTripData?.let { tripData ->

                currentTripData = tripData
                routeCode.value = tripData.routeNumber
                tripData.arrivalName?.let(arrivalName::setValue)
                tripData.departureName?.let(departureName::setValue)
                tripData.departureTime?.let(departureTime::setValue)
            }
        }
    }

    fun onDepartureClick() = router.navigateTo(
        SelectStationScreen(
            mode = SelectStationScreen.MODE_DEPARTURE,
            routeId = sharedPrefs.tripData!!.routeId
        )
    )

    fun onArrivalClick() = router.navigateTo(
        SelectStationScreen(
            mode = SelectStationScreen.MODE_ARRIVAL,
            routeId = sharedPrefs.tripData!!.routeId
        )
    )

    fun onDepartureTimeClick() {

        val onDateSelected = { day: Int, month: Int, year: Int ->
            val newDate = "${"%02d".format(day)}.${"%02d".format(month)}.$year"
            sharedPrefs.tripData = sharedPrefs.tripData?.copy(departureTime = newDate)
            departureTime.value = newDate
        }
        postViewEvents(ViewEventShowDatePicker(onDateSelected))
    }

    fun onCreateClick() {

    }

    private fun checkButtonEnabled() {

        isButtonEnabled.value = sharedPrefs.tripData?.isValid == true
    }

    private fun findRouteByCode(code: String) {

        if (code.isBlank() || code == sharedPrefs.tripData?.routeNumber) return
        searchJob?.cancel()
        searchJob = launch {
            repository.getRoutes().find { it.number == code }?.let { route ->
                needShowLoading.value = true
                val stations = repository.getStationsForRoute(route)
                needShowLoading.value = false
                val dep = stations.first()
                val arr = stations.last()
                sharedPrefs.tripData = TripData(
                    routeId = route.id,
                    routeNumber = route.number,
                    departureId = dep.id,
                    departureName = dep.stopsName,
                    arrivalId = arr.id,
                    arrivalName = arr.stopsName,
                    departureTime = null
                )
                departureTime.value = ""
                departureName.value = dep.stopsName
                arrivalName.value = arr.stopsName
            }
        }
    }
}