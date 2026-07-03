package com.ekaterinael.ui.navgiation.bottom

import com.ekaterinael.ui.R

enum class BottomTab(val iconResId: Int, val textResId: Int) {
    MoodLog(iconResId = R.drawable.note, textResId = R.string.diary),
    Statistic(iconResId = R.drawable.bars, textResId = R.string.statistic);

    companion object {
        val default by lazy { listOf(MoodLog, Statistic) }
    }
}