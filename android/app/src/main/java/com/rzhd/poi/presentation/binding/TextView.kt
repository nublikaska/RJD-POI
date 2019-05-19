package com.rzhd.poi.presentation.binding

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.databinding.BindingAdapter

@BindingAdapter("textColorRes")
fun setColorByRes(textView: TextView, @ColorRes textColorResId: Int) {

    textView.setTextColor(ContextCompat.getColor(textView.context, textColorResId))
}

@BindingAdapter("textBackgroundWithAlpha")
fun setBackgroundAlphaColor(textView: TextView, @ColorRes bgColorResId: Int) {

    val color = ContextCompat.getColor(textView.context, bgColorResId)
    val colorWithAlpha = ColorUtils.setAlphaComponent(color, 0x90)
    textView.backgroundTintList = ColorStateList.valueOf(colorWithAlpha)
}