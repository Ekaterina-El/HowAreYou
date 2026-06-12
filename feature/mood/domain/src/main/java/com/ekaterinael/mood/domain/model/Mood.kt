package com.ekaterinael.mood.domain.model

/** A list describing possible user mood */
enum class Mood(val scope: Int) {
    /** Excellent condition: high energy, positive mood */
    GREAT(scope = 5),

    /** In good condition: everything is gerally fine, and my mood is stable */
    GOOD(scope = 4),

    /** Neutral state: without any strong emotions */
    SO_SO(scope = 3),

    /** Feeling under the weather: low mood, possible accompanied by fatigue or stress */
    BAD(scope = 2),

    /** Very poot condition: severe discomfort, low spirits */
    AWFUL(scope = 1),

    UNKNOWN(scope = -1);

    companion object {
        val all = entries.filterNot { it == UNKNOWN }
        fun Int.toMood() = entries.firstOrNull { it.scope == this } ?: UNKNOWN
    }
}