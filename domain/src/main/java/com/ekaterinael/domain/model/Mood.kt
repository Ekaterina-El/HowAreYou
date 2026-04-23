package com.ekaterinael.domain.model

import com.ekaterinael.resources.R

/** A list describing possible user mood */
enum class Mood(
    val scope: Int,
    val imageResId: Int,
    val titleResId: Int
) {
    /** Excellent condition: high energy, positive mood */
    GREAT(
        scope = 5,
        imageResId = R.drawable.mood_great,
        titleResId = R.string.great_mood
    ),

    /** In good condition: everything is gerally fine, and my mood is stable */
    GOOD(
        scope = 4,
        imageResId = R.drawable.mood_good,
        titleResId = R.string.good_mood
    ),

    /** Neutral state: without any strong emotions */
    SO_SO(
        scope = 3,
        imageResId = R.drawable.mood_so_so,
        titleResId = R.string.so_so_mood
    ),

    /** Feeling under the weather: low mood, possible accompanied by fatigue or stress */
    BAD(
        scope = 2,
        imageResId = R.drawable.mood_bad,
        titleResId = R.string.bad_mood
    ),

    /** Very poot condition: severe discomfort, low spirits */
    AWFUL(
        scope = 1,
        imageResId = R.drawable.mood_awful,
        titleResId = R.string.awful_mood
    ),

    UNKNOWN(
        scope = -1,
        imageResId = R.drawable.ic_launcher_background,
        titleResId = R.string.unknown_mood
    );

    companion object {
        val all = entries.filterNot { it == UNKNOWN }
        fun Int.toMood() = entries.firstOrNull { it.scope == this } ?: UNKNOWN
    }
}