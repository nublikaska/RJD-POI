package com.rzhd.poi.presentation.trip.station.select

import android.content.Context
import com.rzhd.poi.R
import com.rzhd.poi.core.lifecycle.notNullLiveData
import com.rzhd.poi.core.vm.BaseViewModel
import com.rzhd.poi.data.Station
import com.rzhd.poi.data.db.Repository
import com.rzhd.poi.data.prefs.SharedPrefs
import com.rzhd.poi.domain.tripData
import com.rzhd.poi.presentation.base.SelectStationScreen
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.terrakok.cicerone.Router

val selectStationModule = module {

    viewModel { (mode: Int, routeId: String) -> SelectStationViewModel(mode, routeId, get(), get(), get(), get()) }
}

class SelectStationViewModel(
        private val mode: Int,
        private val routeId: String,
        private val router: Router,
        private val repository: Repository,
        private val sharedPrefs: SharedPrefs,
        context: Context
) : BaseViewModel() {

    val needShowLoading by notNullLiveData(false)
    val toolbarTitle by notNullLiveData("")
    val adapter = SimpleStationAdapter()

    private val mapper by lazy { Mapper() }

    init {

        when (mode) {

            SelectStationScreen.MODE_ARRIVAL -> toolbarTitle.value = context.getString(R.string.arrival_point)
            SelectStationScreen.MODE_DEPARTURE -> toolbarTitle.value = context.getString(R.string.departure_point)
        }

        launch {

            needShowLoading.value = true
            val route = repository.getRoutes().find { it.id == routeId }!!
            val mappedStations = repository.getStationsForRoute(route).apply {
                when (mode) {
                    SelectStationScreen.MODE_ARRIVAL -> dropLast(1)
                    SelectStationScreen.MODE_DEPARTURE -> drop(1)
                }
            }.map(mapper)
            adapter.addAll(mappedStations)
            needShowLoading.value = false
        }
    }

    private fun onItemClicked(name: String, id: String) {

        launch {

            val newModel = when (mode) {

                SelectStationScreen.MODE_ARRIVAL -> sharedPrefs.tripData?.copy(
                        arrivalName = name,
                        arrivalId = id
                )

                SelectStationScreen.MODE_DEPARTURE -> sharedPrefs.tripData?.copy(
                        departureName = name,
                        departureId = id
                )

                else -> throw IllegalStateException("unsupported mode")
            }
            sharedPrefs.tripData = newModel
            router.exit()
        }
    }

    private inner class Mapper : (Station) -> SimpleStationItemViewModel {

        override fun invoke(station: Station): SimpleStationItemViewModel = SimpleStationItemViewModel(
                name = station.stopsName,
                id = station.id
        ).apply { onClickLambda = ::onItemClicked }
    }
}