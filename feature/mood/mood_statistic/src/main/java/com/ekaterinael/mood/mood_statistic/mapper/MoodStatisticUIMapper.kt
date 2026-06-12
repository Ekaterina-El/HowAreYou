package com.ekaterinael.mood.mood_statistic.mapper

import com.ekaterinael.mood.mood_statistic.MoodStatisticUI

interface MoodStatisticUIMapper {
    fun map(data: String): MoodStatisticUI
}