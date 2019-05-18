package com.rzhd.poi.presentation.trip.created

import androidx.annotation.ColorRes
import com.rzhd.poi.core.list.DiffListItemModel

data class PresentTripItemViewModel(
        val routeId: String,
        val departureName: String,
        val arrivalName: String,
        val trainNumber: String,
        val status: String,
        @ColorRes val statusColorRes: Int
) : DiffListItemModel {

    lateinit var onClickLambda: (routeId: String) -> Unit

    fun onClick() = onClickLambda.invoke(routeId)

    override fun isSameAs(other: DiffListItemModel): Boolean = equals(other)
}