package com.ekaterinael.mood_list.mapper

import com.ekaterinael.domain.model.MoodLog
import com.ekaterinael.mood_list.MoodListItemUI

interface MoodListUiMapper {
    fun map(logs: List<MoodLog>): List<MoodListItemUI>
}