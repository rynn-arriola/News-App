package com.example.rynnarriola.newsapp.util

import java.util.Calendar
import java.util.concurrent.TimeUnit

fun Calendar.calculateInitialDelay(targetHour: Int): Long {
    val currentTime = Calendar.getInstance()

    set(Calendar.HOUR_OF_DAY, targetHour)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)

    return if (currentTime.before(this)) {
        timeInMillis - currentTime.timeInMillis
    } else {
        timeInMillis + TimeUnit.HOURS.toMillis(24) - currentTime.timeInMillis
    }
}