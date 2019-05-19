package com.rzhd.poi.presentation.trip.poi

import com.rzhd.poi.core.vm.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val poiModule = module {

    viewModel { PoiViewModel() }
}

class PoiViewModel : BaseViewModel()