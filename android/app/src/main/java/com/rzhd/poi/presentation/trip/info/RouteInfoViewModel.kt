package com.rzhd.poi.presentation.trip.info

import com.rzhd.poi.core.lifecycle.notNullLiveData
import com.rzhd.poi.core.vm.BaseViewModel
import com.rzhd.poi.data.*
import com.rzhd.poi.data.db.Repository
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.terrakok.cicerone.Router
import java.util.Calendar

val routeInfoModule = module {

    viewModel { (routeId: String) -> RouteInfoViewModel(routeId, get(), get()) }
}

class RouteInfoViewModel(
        private val routeId: String,
        private val repository: Repository,
        private val router: Router
) : BaseViewModel() {

    val needShowLoading by notNullLiveData(false)
    val needShowCurrent by notNullLiveData(false)
    val currentTimeLeft by notNullLiveData("")
    val passedPercentLeft by notNullLiveData("")
    val title by notNullLiveData("")

    val adapter = RouteInfoAdapter()

    private val mapper = Mapper()

    init {

        launch {

            needShowLoading.value = true
            val trip = repository.getUserTrip(routeId)
            val route = repository.getRoute(routeId)
            title.value = "Маршрут поезда №${route.number}"
            val stations = repository.getStationsForRoute(route)

            val tripDeparture = route.departure.asTime
            val tripEndTime = trip.departureDate.asCalendar.apply {
                set(Calendar.HOUR_OF_DAY, tripDeparture.hours)
                set(Calendar.MINUTE, tripDeparture.minutes)
                add(Calendar.MINUTE, route.travelTime)
            }
            val tripStartTime = trip.departureDate.asCalendar.apply {
                set(Calendar.HOUR_OF_DAY, tripDeparture.hours)
                set(Calendar.MINUTE, tripDeparture.minutes)
            }

            val now = currentDate
            needShowCurrent.value = now < tripEndTime && now > tripStartTime
            val totalTimeOfTrip = tripEndTime.timeInMillis - tripStartTime.timeInMillis
            val hoursLeft = totalTimeOfTrip / (1000 * 60 * 60)
            val minutesLeft = totalTimeOfTrip / (1000 * 60) - hoursLeft * 60
            currentTimeLeft.value = "Осталось %d часа %d минут".format(hoursLeft, minutesLeft)
            val percentPassed = (((now.timeInMillis - tripStartTime.timeInMillis).toFloat() / totalTimeOfTrip) * 100).toInt()
            passedPercentLeft.value = "Пройдено %d%%".format(percentPassed)

            adapter.addAll(mapper.invoke(route, stations))
            needShowLoading.value = false
        }
    }

    //TODO router.navigateTo(StationInfoScreen(stationId))
    private fun onStationClick(stationId: String) = Unit

    private inner class Mapper : (Route, List<Station>) -> List<RouteInfoItemViewModel> {

        override fun invoke(route: Route, stations: List<Station>): List<RouteInfoItemViewModel> = stations
                .mapIndexed { index, station ->

                    RouteInfoItemViewModel(
                            stopId = station.id,
                            stopName = station.stopsName,
                            stopRegion = station.areaName,
                            stopGlobalTime = "08:15",
                            stopTime = when (index) {
                                0, stations.lastIndex -> ""
                                else -> "%d мин.".format(station.stopTime)
                            },
                            isFirstInRegion = false,
                            isPassed = false,
                            isGoodForGo = station.stopTime > 15,
                            regionPicRef = null
                    ).apply { onClickLambda = ::onStationClick }
                }
    }
}