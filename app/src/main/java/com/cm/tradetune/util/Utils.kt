package com.cm.tradetune.util

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    // Function to get the current time in a specified format
    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentDate = Date(System.currentTimeMillis())
        return dateFormat.format(currentDate)
    }

    // Function to generate a unique ID (for demonstration purposes)
    fun generateUniqueId(): Long {
        return System.currentTimeMillis()
    }
}
