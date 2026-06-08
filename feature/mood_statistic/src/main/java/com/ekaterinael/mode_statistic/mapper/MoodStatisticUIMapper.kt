package com.ekaterinael.mode_statistic.mapper

import com.ekaterinael.mode_statistic.MoodStatisticUI

interface MoodStatisticUIMapper {
    fun map(data: String): MoodStatisticUI
}