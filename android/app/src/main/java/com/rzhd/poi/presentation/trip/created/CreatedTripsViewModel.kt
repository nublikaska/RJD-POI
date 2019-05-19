package com.rzhd.poi.presentation.trip.created

import com.rzhd.poi.R
import com.rzhd.poi.core.lifecycle.notNullLiveData
import com.rzhd.poi.core.vm.BaseViewModel
import com.rzhd.poi.data.*
import com.rzhd.poi.data.db.Repository
import com.rzhd.poi.domain.asStorageName
import com.rzhd.poi.domain.storage
import com.rzhd.poi.presentation.base.CreateTripScreen
import com.rzhd.poi.presentation.base.RouteInfoScreen
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.terrakok.cicerone.Router
import java.util.Calendar

val createdTripsModule = module {

    viewModel { CreatedTripsViewModel(get(), get()) }
}

class CreatedTripsViewModel(
        private val router: Router,
        private val repository: Repository
) : BaseViewModel() {

    val needShowLoading by notNullLiveData(false)
    val adapter = PresentTripAdapter()

    private val mapper = Mapper()

    init {

        launch {

            needShowLoading.value = true
            val userTrips = repository.getUserTrips().sortedBy { it.departureDate.asCalendar }
            val correspondingRoutes = userTrips.map { repository.getRoute(it.routeId) }
            val correspondingStations = correspondingRoutes.map { repository.getStationsForRoute(it) }
            val models = userTrips.mapIndexed { index, userTrip ->
                mapper.invoke(userTrip, correspondingRoutes[index], correspondingStations[index])
            }
            adapter.addAll(models)
            needShowLoading.value = false
        }
    }

    fun onNewTripClick() = router.navigateTo(CreateTripScreen)

    private fun onTripClicked(routeId: String) = router.navigateTo(RouteInfoScreen(routeId))

    private inner class Mapper : (UserTrip, Route, List<Station>) -> PresentTripItemViewModel {

        override fun invoke(trip: UserTrip,
                            route: Route,
                            stations: List<Station>): PresentTripItemViewModel {

            val tripEndTime = trip.departureDate.asCalendar.apply { add(Calendar.MINUTE, route.travelTime) }
            val tripStartTime = trip.departureDate.asCalendar

            val arrivalName = stations.find { it.id == trip.arrivalId }!!
            val departureName = stations.find { it.id == trip.departureId }!!

            return PresentTripItemViewModel(
                    routeId = route.id,
                    departureName = departureName.stopsName,
                    arrivalName = arrivalName.stopsName,
                    trainNumber = "Поезд №%s".format(route.number),
                    statusColorRes = when (currentDate > tripEndTime || currentDate < tripStartTime) {
                        true -> R.color.grey
                        false -> R.color.green
                    },
                    status = when (currentDate > tripEndTime || currentDate < tripStartTime) {
                        true -> "Путешествие с %s по %s".format(tripStartTime.asDateString, tripEndTime.asDateString)
                        false -> "Текущее путешествие"
                    },
                    departureRegionLink = storage.getReference("images/${departureName.areaName.asStorageName}.png"),
                    arrivalRegionLink = storage.getReference("images/${arrivalName.areaName.asStorageName}.png")
            ).apply { onClickLambda = ::onTripClicked }
        }
    }
}