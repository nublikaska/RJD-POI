package com.rzhd.poi.presentation.trip.station.info

import com.rzhd.poi.core.vm.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val stationInfoModule = module {

    viewModel { StationInfoViewModel() }
}

class StationInfoViewModel : BaseViewModel()