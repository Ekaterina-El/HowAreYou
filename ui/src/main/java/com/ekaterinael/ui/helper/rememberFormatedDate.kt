package com.ekaterinael.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.ekaterinael.core.ext.toFullUserString
import java.util.Date

@Composable
fun rememberFormatedDate(date: Date?): String {
    if (date == null) return ""
    val locale = getLocale()

    return remember(date, locale) {
        date.toFullUserString(locale)
    }
}