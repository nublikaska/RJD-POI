package com.rzhd.poi.data

import java.util.Calendar

inline val currentDate: Calendar
    get() = Calendar.getInstance()

inline val Calendar.asDateString: String
    get() = "${get(Calendar.DAY_OF_MONTH)}.${get(Calendar.MONTH)}.${get(Calendar.YEAR)}"

inline val String.asCalendar: Calendar
    get() {

        val (day: Int, month: Int, year: Int) = split('.').map { it.toInt() }
        return Calendar.getInstance().apply {
            clear()
            set(year, month, day)
        }
    }