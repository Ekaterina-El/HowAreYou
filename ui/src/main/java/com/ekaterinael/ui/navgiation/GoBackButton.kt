package com.ekaterinael.ui.navgiation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun GoBackButton(onGoBack: () -> Unit) {
    Icon(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .clickable(onClick = onGoBack)
            .padding(3.dp),
        imageVector = Icons.Filled.ArrowBackIosNew,
        tint = MaterialTheme.colorScheme.onBackground,
        contentDescription = null
    )
}
