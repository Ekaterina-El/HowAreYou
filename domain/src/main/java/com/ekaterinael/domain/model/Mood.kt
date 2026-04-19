package com.ekaterinael.domain.model

/** A list describing possible user mood */
enum class Mood(val scope: Int) {
    /** Excellent condition: high energy, positive mood */
    GREAT(5),

    /** In good condition: everything is gerally fine, and my mood is stable */
    GOOD(4),

    /** Neutral state: without any strong emotions */
    SO_SO(3),

    /** Feeling under the weather: low mood, possible accompanied by fatigue or stress */
    BAD(2),

    /** Very poot condition: severe discomfort, low spirits */
    AWFUL(1)
}