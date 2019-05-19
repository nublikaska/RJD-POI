package com.rzhd.poi.presentation.trip.station.info

import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class StationInfoFragment : BaseFragment<StationInfoViewModel>() {

    override val dataBindingVariable = BR.vmStationInfo
    override val layoutId = R.layout.fragment_station
    override val viewModel: StationInfoViewModel by viewModel()
}