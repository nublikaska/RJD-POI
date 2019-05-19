package com.rzhd.poi.data

import java.util.Calendar

inline val currentDate: Calendar
    get() = Calendar.getInstance()

inline val Calendar.asDateString: String
    get() = "${get(Calendar.DAY_OF_MONTH)}.${get(Calendar.MONTH) + 1}.${get(Calendar.YEAR)}"

inline val String.asCalendar: Calendar
    get() {

        val (day: Int, month: Int, year: Int) = split('.').map { it.toInt() }
        return Calendar.getInstance().apply {
            clear()
            set(year, month - 1, day)
        }
    }

inline val String.asTime: Time
    get() {

        val (hours: Int, minutes: Int) = split(':').map { it.toInt() }
        return Time(hours, minutes)
    }

data class Time(val hours: Int, val minutes: Int)