package com.rzhd.poi.presentation.trip.info

import com.rzhd.poi.core.vm.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val routeInfoModule = module {

    viewModel { (routeId: String) -> RouteInfoViewModel(routeId) }
}

class RouteInfoViewModel(private val routeId: String) : BaseViewModel()