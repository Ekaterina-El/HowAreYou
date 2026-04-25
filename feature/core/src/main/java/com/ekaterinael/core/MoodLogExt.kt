package com.ekaterinael.core

import com.ekaterinael.domain.model.MoodLogDTO
import com.ekaterinael.utils.toUserString
import java.util.Locale

fun MoodLogDTO.dateUserString(locale: Locale): String = this.date.toUserString(locale)