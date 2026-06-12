package com.ekaterinael.ui.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun AppIconButton(
    image: ImageVector,
    innerPadding: Dp = 8.dp,
    onClick: (() -> Unit)? = null
) {
    val isActive = onClick != null
    val outlineColor = MaterialTheme.colorScheme.outline
    val color = if (isActive) MaterialTheme.colorScheme.secondary else outlineColor

    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .border(width = 1.dp, color = outlineColor, shape = CircleShape)
            .let {
                if (!isActive) return@let it
                else it.clickable(enabled = true, onClick = onClick)
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            imageVector = image,
            tint = color,
            contentDescription = null
        )
    }
}