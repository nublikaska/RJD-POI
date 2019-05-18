package com.rzhd.poi.presentation.trip.created

import com.rzhd.poi.core.lifecycle.notNullLiveData
import com.rzhd.poi.core.vm.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createdTripsModule = module {

    viewModel { CreatedTripsViewModel() }
}

class CreatedTripsViewModel : BaseViewModel() {

    val needShowLoading by notNullLiveData(false)
}