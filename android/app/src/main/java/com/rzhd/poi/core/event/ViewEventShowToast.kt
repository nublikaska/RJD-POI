package com.rzhd.poi.core.event

import android.widget.Toast
import androidx.fragment.app.Fragment

class ViewEventShowToast(
        private val text: String,
        private val length: Int = Toast.LENGTH_LONG
) : ViewEvent {

    override fun execute(fragment: Fragment) = Toast.makeText(fragment.context, text, length).show()
}