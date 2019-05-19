package com.rzhd.poi.presentation.trip.created

import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatedTripsFragment : BaseFragment<CreatedTripsViewModel>() {

    override val dataBindingVariable = BR.vmCreatedTrips
    override val layoutId = R.layout.fragment_created_trips
    override val viewModel: CreatedTripsViewModel by viewModel()
}