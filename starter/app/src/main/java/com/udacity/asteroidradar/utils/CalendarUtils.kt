package com.udacity.asteroidradar.utils

import com.udacity.asteroidradar.Constants
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarUtils {
    companion object{
        fun getTonightFormatted() : String{
            val calendar = Calendar.getInstance()
            val currentTime = calendar.time
            val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
            return dateFormat.format(currentTime)
        }

        fun getNextSevenDaysFormattedDates(): ArrayList<String> {
            val formattedDateList = ArrayList<String>()

            val calendar = Calendar.getInstance()
            for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
                val currentTime = calendar.time
                val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
                formattedDateList.add(dateFormat.format(currentTime))
                calendar.add(Calendar.DAY_OF_YEAR, 1)
            }

            return formattedDateList
        }
    }
}