package com.rzhd.poi.presentation.binding

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible", "useInvisible", requireAll = false)
fun setViewVisible(view: View, isVisible: Boolean?, useInvisible: Boolean?) {

    isVisible ?: return
    when (useInvisible) {

        true -> view.isInvisible = isVisible.not()
        else -> view.isVisible = isVisible
    }
}