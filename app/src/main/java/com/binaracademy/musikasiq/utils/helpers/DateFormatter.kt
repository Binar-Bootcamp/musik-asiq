package com.binaracademy.musikasiq.utils.helpers

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {

    companion object {
        fun fromISOToFormatString(isoDateString: String, format: String = "MMM dd, yyyy"): String {
            val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale("ID", "id"))
            val date: Date? = isoFormat.parse(isoDateString)

            val outputFormat = SimpleDateFormat(format, Locale("ID", "id"))
            return outputFormat.format(date ?: Date())
        }
    }

}