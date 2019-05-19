package com.rzhd.poi.core.event

import android.app.DatePickerDialog
import androidx.fragment.app.Fragment
import com.rzhd.poi.data.currentDate
import java.util.Calendar

class ViewEventShowDatePicker(private val onDateSet: (day: Int, month: Int, year: Int) -> Unit) : ViewEvent {

    override fun execute(fragment: Fragment) {

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            onDateSet.invoke(dayOfMonth, month + 1, year)
        }
        val calendar = currentDate
        DatePickerDialog(
                fragment.context ?: return,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}