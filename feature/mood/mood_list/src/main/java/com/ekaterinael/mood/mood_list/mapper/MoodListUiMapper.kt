package com.ekaterinael.mood.mood_list.mapper

import com.ekaterinael.mood.domain.model.MoodLog
import com.ekaterinael.mood.mood_list.MoodListItemUI

interface MoodListUiMapper {
    fun map(logs: List<MoodLog>): List<MoodListItemUI>
}