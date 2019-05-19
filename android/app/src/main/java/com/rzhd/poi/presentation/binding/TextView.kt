package com.rzhd.poi.presentation.binding

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("textColorRes")
fun setColorByRes(textView: TextView, @ColorRes textColorResId: Int) {

    textView.setTextColor(ContextCompat.getColor(textView.context, textColorResId))
}