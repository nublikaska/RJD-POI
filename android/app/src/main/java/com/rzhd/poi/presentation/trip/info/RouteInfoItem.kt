package com.rzhd.poi.presentation.trip.info

import com.google.firebase.storage.StorageReference
import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.list.DiffListItemModel
import com.rzhd.poi.core.list.SingleItemLayoutDiffListAdapter

data class RouteInfoItemViewModel(
        val stopId: String,
        val stopName: String,
        val stopRegion: String,
        val stopGlobalTime: String,
        val stopTime: String,
        val isGoodForGo: Boolean,
        val isFirstInRegion: Boolean,
        val isPassed: Boolean,
        val regionPicRef: StorageReference? = null
) : DiffListItemModel {

    lateinit var onClickLambda: (stopId: String) -> Unit

    fun onClick() = onClickLambda.invoke(stopId)

    override fun isSameAs(other: DiffListItemModel): Boolean = equals(other)
}

class RouteInfoAdapter : SingleItemLayoutDiffListAdapter<RouteInfoItemViewModel>(
        itemLayoutId = R.layout.li_station_trip,
        dataBindingVariable = BR.vmStationTrip
)