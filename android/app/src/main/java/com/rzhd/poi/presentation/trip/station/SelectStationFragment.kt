package com.rzhd.poi.presentation.trip.station

import android.os.Parcelable
import androidx.core.os.bundleOf
import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.ui.BaseFragment
import kotlinx.android.parcel.Parcelize
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SelectStationFragment : BaseFragment<SelectStationViewModel>() {

    companion object {

        private const val ARGS_KEY = "args"

        fun getInstance(mode: Int, routeId: String): SelectStationFragment = SelectStationFragment().apply {

            arguments = bundleOf(ARGS_KEY to Params(mode, routeId))
        }
    }

    override val dataBindingVariable = BR.vmSelectStation
    override val layoutId = R.layout.fragment_select_station
    override val viewModel: SelectStationViewModel by viewModel(parameters = {
        val model = arguments?.getParcelable<Params>(ARGS_KEY)
        parametersOf(model?.mode, model?.routeId)
    })

    @Parcelize
    private data class Params(val mode: Int, val routeId: String) : Parcelable
}