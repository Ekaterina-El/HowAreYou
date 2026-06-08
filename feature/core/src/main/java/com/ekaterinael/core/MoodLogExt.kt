package com.ekaterinael.core

import com.ekaterinael.utils.toFullUserString
import java.util.Date
import java.util.Locale

fun Date.dateUserString(locale: Locale): String = this.toFullUserString(locale)