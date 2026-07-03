package com.ekaterinael.ui.effects.animation

import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimator

fun slideFromBottom(): StackAnimator {
    return verticalSlide(multiplier = 1f)
}

fun verticalSlide(multiplier: Float): StackAnimator {
    return stackAnimator(animationSpec = tween(300)) { factor, _, content ->
        content(
            Modifier.offsetYFactor(factor  * multiplier)
        )
    }
}

private fun Modifier.offsetYFactor(factor: Float): Modifier =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        layout(placeable.width, placeable.height) {
            placeable.placeRelative(
                x = 0,
                y = (placeable.width * factor).toInt()
            )
        }
    }