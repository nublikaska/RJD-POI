package com.rzhd.poi.presentation.trip.create

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createTripModule = module {

    viewModel { CreateTripViewModel(get(), get(), get()) }
}