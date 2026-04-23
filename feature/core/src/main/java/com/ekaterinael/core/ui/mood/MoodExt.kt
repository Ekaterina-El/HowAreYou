package com.ekaterinael.core.ui.mood

import androidx.compose.ui.graphics.Color
import com.ekaterinael.core.ui.theme.AwfulMood
import com.ekaterinael.core.ui.theme.BadMood
import com.ekaterinael.core.ui.theme.GoodMood
import com.ekaterinael.core.ui.theme.GreatMood
import com.ekaterinael.core.ui.theme.SoSoMood
import com.ekaterinael.domain.model.Mood

fun Mood.color(): Color {
    return when (this) {
        Mood.GREAT -> GreatMood
        Mood.GOOD -> GoodMood
        Mood.SO_SO -> SoSoMood
        Mood.BAD -> BadMood
        Mood.AWFUL -> AwfulMood
        Mood.UNKNOWN -> AwfulMood
    }
}