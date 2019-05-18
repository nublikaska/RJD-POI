package com.rzhd.poi.presentation.binding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun setViewVisible(view: View, isVisible: Boolean?) {

    view.isVisible = isVisible == true
}