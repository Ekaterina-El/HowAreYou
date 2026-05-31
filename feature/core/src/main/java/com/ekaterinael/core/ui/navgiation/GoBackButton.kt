package com.ekaterinael.core.ui.navgiation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GoBackButton(onGoBack: () -> Unit) {
    Icon(
        modifier = Modifier.size(25.dp).clickable(onClick = onGoBack),
        imageVector = Icons.Filled.ArrowBackIosNew,
        tint = MaterialTheme.colorScheme.onBackground,
        contentDescription = null
    )
}
