package com.ekaterinael.core.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.triangleGradient(
    colorTop: Color,
    colorLeft: Color,
    colorRight: Color
) {
    drawRect(
        brush = Brush.radialGradient(
            colors = listOf(
                colorTop,
                Color.Transparent
            ),
            center = Offset(size.width / 2, 0f),
            radius = size.width
        )
    )

    drawRect(
        brush = Brush.radialGradient(
            colors = listOf(
                colorLeft,
                Color.Transparent
            ),
            center = Offset(0f, size.height),
            radius = size.width * 0.9f
        )
    )

    drawRect(
        brush = Brush.radialGradient(
            colors = listOf(
                colorRight,
                Color.Transparent
            ),
            center = Offset(size.width, size.height),
            radius = size.width * 0.9f
        )
    )
}