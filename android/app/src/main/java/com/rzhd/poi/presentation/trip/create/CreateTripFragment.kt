package com.rzhd.poi.presentation.trip.create

import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateTripFragment : BaseFragment<CreateTripViewModel>() {

    override val dataBindingVariable = BR.vmCreateTrip
    override val layoutId = R.layout.fragment_create_trip
    override val viewModel: CreateTripViewModel by viewModel()
}