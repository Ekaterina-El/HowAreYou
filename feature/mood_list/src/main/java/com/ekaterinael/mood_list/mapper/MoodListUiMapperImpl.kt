package com.ekaterinael.mood_list.mapper

import com.ekaterinael.domain.model.MoodLog
import com.ekaterinael.mood_list.MoodListItemUI
import com.ekaterinael.mood_list.di.MoodListScope
import javax.inject.Inject

@MoodListScope
class MoodListUiMapperImpl @Inject constructor() : MoodListUiMapper {
    override fun map(logs: List<MoodLog>): List<MoodListItemUI> = logs.map {
        MoodListItemUI(
            id = it.id,
            date = it.date,
            description = it.description,
            moodImageResId = it.mood.imageResId,
            moodTitleId = it.mood.titleResId
        )
    }
}