package com.rzhd.poi.presentation.trip.info

import androidx.core.os.bundleOf
import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RouteInfoFragment : BaseFragment<RouteInfoViewModel>() {

    companion object {

        private const val ROUTE_ID_KEY = "route_id"

        fun getInstance(routeId: String): RouteInfoFragment = RouteInfoFragment().apply {
            arguments = bundleOf(ROUTE_ID_KEY to routeId)
        }
    }

    override val dataBindingVariable = BR.vmRouteInfo
    override val layoutId = R.layout.fragment_route_info
    override val viewModel: RouteInfoViewModel by viewModel {
        parametersOf(arguments?.getString(ROUTE_ID_KEY).orEmpty())
    }
}