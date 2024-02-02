package com.cm.tradetune.util

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

class TimeUtil {
    companion object {
        fun String.toMillis(): Long {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            return dateFormat.parse(this)?.time ?: 0
        }
    }
}

fun Long.toTimeAgo(): String {
    val now = System.currentTimeMillis()
    val timeDifference = now - this

    return when {

        timeDifference >= DateUtils.WEEK_IN_MILLIS -> {
            // If the time difference is more than or equal to an hour
            val weeksAgo = timeDifference / DateUtils.WEEK_IN_MILLIS
            "$weeksAgo ${if (weeksAgo > 1) "weeks" else "week"} ago"
        }
        timeDifference >= DateUtils.DAY_IN_MILLIS -> {
            // If the time difference is more than or equal to an hour
            val daysAgo = timeDifference / DateUtils.DAY_IN_MILLIS
            "$daysAgo ${if (daysAgo > 1) "days" else "days"} ago"
        }
        timeDifference >= DateUtils.HOUR_IN_MILLIS -> {
            // If the time difference is more than or equal to an hour
            val hoursAgo = timeDifference / DateUtils.HOUR_IN_MILLIS
            "$hoursAgo ${if (hoursAgo > 1) "hours" else "hour"} ago"
        }
        timeDifference >= DateUtils.MINUTE_IN_MILLIS -> {
            // If the time difference is more than or equal to a minute
            val minutesAgo = timeDifference / DateUtils.MINUTE_IN_MILLIS
            "$minutesAgo ${if (minutesAgo > 1) "minutes" else "minute"} ago"
        }
        else -> {
            // If the time difference is less than a minute
            val secondsAgo = timeDifference / DateUtils.SECOND_IN_MILLIS
            "$secondsAgo ${if (secondsAgo > 1) "seconds" else "second"} ago"
        }
    }
}
