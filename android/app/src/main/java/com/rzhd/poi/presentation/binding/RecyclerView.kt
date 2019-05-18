package com.rzhd.poi.presentation.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rzhd.poi.core.list.DiffListAdapter
import com.rzhd.poi.core.list.DiffListItemModel

@BindingAdapter("adapter")
fun <T : DiffListItemModel> setRecyclerViewAdapter(rv: RecyclerView, adapter: DiffListAdapter<T>?) {

    rv.adapter = adapter
}

@BindingAdapter("fixedSize")
fun setFixedSize(rv: RecyclerView, fixedSize: Boolean) {

    rv.setHasFixedSize(fixedSize)
}