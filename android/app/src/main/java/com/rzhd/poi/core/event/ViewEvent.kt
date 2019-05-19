package com.rzhd.poi.core.event

import androidx.fragment.app.Fragment

interface ViewEvent {

    fun execute(fragment: Fragment)
}