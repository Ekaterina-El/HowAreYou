package com.ekaterinael.mood.core

import com.ekaterinael.mood.domain.model.Mood

fun Mood.toUI(): MoodUI {
    return when (this) {
        Mood.GREAT -> MoodUI.Great
        Mood.GOOD -> MoodUI.Good
        Mood.SO_SO -> MoodUI.SoSo
        Mood.BAD -> MoodUI.Bag
        Mood.AWFUL -> MoodUI.Awful
        Mood.UNKNOWN -> MoodUI.Unknown
    }
}