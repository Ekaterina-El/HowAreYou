package com.ekaterinael.mode_statistic.mapper

import com.ekaterinael.mode_statistic.MoodStatisticUI
import com.ekaterinael.mode_statistic.di.MoodStatisticScope
import javax.inject.Inject

@MoodStatisticScope
class MoodStatisticUIMapperImpl @Inject constructor() : MoodStatisticUIMapper {
    override fun map(data: String) = MoodStatisticUI(data = data)
}