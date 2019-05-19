package com.rzhd.poi.presentation.binding

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils
import androidx.databinding.BindingAdapter

@BindingAdapter("textColorRes")
fun setColorByRes(textView: TextView, @ColorInt textColor: Int) {

    textView.setTextColor(textColor)
}

@BindingAdapter("textBackgroundWithAlpha")
fun setBackgroundAlphaColor(textView: TextView, @ColorInt bgColor: Int) {

    val colorWithAlpha = ColorUtils.setAlphaComponent(bgColor, 0x90)
    textView.backgroundTintList = ColorStateList.valueOf(colorWithAlpha)
}