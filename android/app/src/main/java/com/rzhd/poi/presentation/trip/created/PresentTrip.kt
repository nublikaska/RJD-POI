package com.rzhd.poi.presentation.trip.created

import androidx.annotation.ColorRes
import com.google.firebase.storage.StorageReference
import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.list.DiffListItemModel
import com.rzhd.poi.core.list.SingleItemLayoutDiffListAdapter

data class PresentTripItemViewModel(
        val routeId: String,
        val departureName: String,
        val arrivalName: String,
        val trainNumber: String,
        val status: String,
        @ColorRes val statusColorRes: Int,
        val departureRegionLink: StorageReference,
        val arrivalRegionLink: StorageReference
) : DiffListItemModel {

    lateinit var onClickLambda: (routeId: String) -> Unit

    fun onClick() = onClickLambda.invoke(routeId)

    override fun isSameAs(other: DiffListItemModel): Boolean = equals(other)
}

class PresentTripAdapter : SingleItemLayoutDiffListAdapter<PresentTripItemViewModel>(
        itemLayoutId = R.layout.li_present_trip,
        dataBindingVariable = BR.vmPresentTrip
)