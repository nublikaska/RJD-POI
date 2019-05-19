package com.rzhd.poi.presentation.binding

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.google.firebase.storage.StorageReference
import com.rzhd.poi.GlideApp

@BindingAdapter("storageReference")
fun setImageByStorageReference(imageView: ImageView, storageReference: StorageReference?) {

    storageReference ?: return
    GlideApp.with(imageView.context)
            .load(storageReference)
            .into(imageView)
}

@BindingAdapter("tintColorRes")
fun tintImageViewByRes(imageView: ImageView, @ColorRes tintColorResId: Int) {

    ImageViewCompat.setImageTintList(
            imageView,
            ColorStateList.valueOf(ContextCompat.getColor(imageView.context, tintColorResId))
    )
}