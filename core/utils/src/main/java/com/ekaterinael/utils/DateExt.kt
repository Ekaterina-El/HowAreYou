package com.ekaterinael.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toFullUserString(locale: Locale): String {
    val formatter = SimpleDateFormat("EEEE, d MMMM H:mm", locale)
    return formatter.format(this)
}

fun Date.toShortUserString(locale: Locale): String {
    val formatter = SimpleDateFormat("LLLL yyyy", locale)
    return formatter.format(this).capitalizeFirstLetter()
}