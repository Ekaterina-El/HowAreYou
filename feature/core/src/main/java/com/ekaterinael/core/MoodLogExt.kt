package com.ekaterinael.core

import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.utils.toFullUserString
import java.util.Locale

fun MoodLogDTO.dateUserString(locale: Locale): String = this.date.toFullUserString(locale)