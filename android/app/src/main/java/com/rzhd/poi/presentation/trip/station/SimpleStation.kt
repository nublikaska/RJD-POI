package com.rzhd.poi.presentation.trip.station

import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.list.DiffListItemModel
import com.rzhd.poi.core.list.SingleItemLayoutDiffListAdapter

data class SimpleStationItemViewModel(
    val name: String,
    val id: String
) : DiffListItemModel {

    lateinit var onClickLambda: (name: String, id: String) -> Unit

    fun onClick() = onClickLambda.invoke(name, id)

    override fun isSameAs(other: DiffListItemModel) = (other as? SimpleStationItemViewModel)?.id == id
}

class SimpleStationAdapter : SingleItemLayoutDiffListAdapter<SimpleStationItemViewModel>(
    itemLayoutId = R.layout.li_station_select,
    dataBindingVariable = BR.vmSimpleStation
)