package com.rzhd.poi.presentation.trip.station

import android.content.Context
import com.rzhd.poi.R
import com.rzhd.poi.core.lifecycle.notNullLiveData
import com.rzhd.poi.core.vm.BaseViewModel
import com.rzhd.poi.data.db.Repository
import com.rzhd.poi.presentation.base.SelectStationScreen
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.terrakok.cicerone.Router

val selectStationModule = module {

    viewModel { (mode: Int, routeId: String) -> SelectStationViewModel(mode, routeId, get(), get(), get()) }
}

class SelectStationViewModel(
    private val mode: Int,
    private val routeId: String,
    private val router: Router,
    private val repository: Repository,
    context: Context
) : BaseViewModel() {

    val toolbarTitle by notNullLiveData("")

    init {

        when (mode) {

            SelectStationScreen.MODE_ARRIVAL -> toolbarTitle.value = context.getString(R.string.arrival_point)
            SelectStationScreen.MODE_DEPARTURE -> toolbarTitle.value = context.getString(R.string.departure_point)
        }
    }
}