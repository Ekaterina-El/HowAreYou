package com.ekaterinael.howareyou.navigation

import com.ekaterinael.howareyou.R

enum class BottomTab(val iconResId: Int, val textResId: Int) {
    MoodLog(iconResId = R.drawable.note, textResId = R.string.diary),
    Statistic(iconResId = R.drawable.bars, textResId = R.string.statistic);

    companion object {
        val default by lazy { listOf(MoodLog, Statistic) }
    }
}