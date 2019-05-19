package com.rzhd.poi.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.firebase.storage.StorageReference
import com.rzhd.poi.GlideApp

@BindingAdapter("storageReference")
fun setImageByStorageReference(imageView: ImageView, storageReference: StorageReference) {

    GlideApp.with(imageView.context)
        .load(storageReference)
        .into(imageView)
}