package com.rzhd.poi.core.list

import androidx.annotation.LayoutRes

open class SingleItemLayoutDiffListAdapter<T : DiffListItemModel>(

        @LayoutRes private val itemLayoutId: Int,
        private val dataBindingVariable: Int

) : DiffListAdapter<T>() {

    final override fun getListItemLayoutId(type: Int) = itemLayoutId

    final override fun fillHolderViews(holder: BaseViewHolder?, item: T, viewType: Int, payloads: MutableList<Any>?) {

        holder?.binding?.setVariable(dataBindingVariable, item)
    }
}