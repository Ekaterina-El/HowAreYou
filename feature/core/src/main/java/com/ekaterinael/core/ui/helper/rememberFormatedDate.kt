package com.ekaterinael.core.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.ekaterinael.core.dateUserString
import java.util.Date

@Composable
fun rememberFormatedDate(date: Date): String {
    val locale = getLocale()

    return remember(date, locale) {
        date.dateUserString(locale).uppercase(locale)
    }
}