package com.rzhd.poi.presentation.trip.poi

import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PoiFragment : BaseFragment<PoiViewModel>() {

    override val dataBindingVariable = BR.vmPoi
    override val layoutId = R.layout.fragment_poi
    override val viewModel: PoiViewModel by viewModel()
}