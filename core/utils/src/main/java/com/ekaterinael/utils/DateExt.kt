package com.ekaterinael.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toUserString(locale: Locale): String {
    val formatter = SimpleDateFormat("EEEE, d MMMM H:mm", locale)
    return formatter.format(this)
}
