package com.ekaterinael.mood_list

import java.util.Date

data class MoodListItemUI(
    val id: Long? = null,
    val date: Date,
    val description: String,
    val moodImageResId: Int,
    val moodTitleId: Int
)