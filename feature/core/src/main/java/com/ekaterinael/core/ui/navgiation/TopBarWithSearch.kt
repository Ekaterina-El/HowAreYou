package com.ekaterinael.core.ui.navgiation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekaterinael.core.ui.buttons.AppIconButton
import com.ekaterinael.core.ui.theme.HowAreYouTheme

@Composable
fun TopBarWithSearch(
    title: String,
    modifier: Modifier = Modifier,
    onClickBack: (() -> Unit)? = null,
    onClickForward: (() -> Unit)? = null,
    onClickSearch: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth().then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconButton(
            image = Icons.Default.ArrowBackIosNew,
            onClick = onClickBack
        )
        Spacer(Modifier.width(5.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.width(5.dp))
        AppIconButton(
            image = Icons.AutoMirrored.Default.ArrowForwardIos,
            onClick = onClickForward
        )
        Spacer(Modifier.width(12.dp))
        AppIconButton(
            image = Icons.Outlined.Search,
            innerPadding = 6.dp,
            onClick = onClickSearch
        )
    }
}


@Preview
@Composable
private fun TopBarWithSearchPreview() {
    HowAreYouTheme(darkTheme = true) {
        Box(Modifier.background(MaterialTheme.colorScheme.background)) {
            TopBarWithSearch(
                title = "Январь 2026",
                onClickSearch = {},
                onClickForward = {},
            )
        }
    }
}