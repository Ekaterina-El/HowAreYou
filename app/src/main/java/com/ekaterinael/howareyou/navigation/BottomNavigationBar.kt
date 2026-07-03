package com.ekaterinael.howareyou.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekaterinael.ui.theme.HowAreYouTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    tabs: List<BottomTab>,
    selectedTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
        )

        NavigationBar(modifier = modifier) {
            tabs.forEach {
                AppNavigationBarItem(
                    item = it,
                    isSelected = it == selectedTab
                ) { onTabSelected(it) }
            }
        }
    }
}

@Composable
private fun RowScope.AppNavigationBarItem(
    modifier: Modifier = Modifier,
    item: BottomTab,
    isSelected: Boolean,
    onTabSelected: () -> Unit,

    ) {
    val baseColor = MaterialTheme.colorScheme.onSurfaceVariant
    val selectedColor = MaterialTheme.colorScheme.primary

    val contentColor = remember(isSelected) { if (isSelected) selectedColor else baseColor }
    val animatedContentColor by animateColorAsState(
        targetValue = contentColor,
        animationSpec = tween(durationMillis = 250),
    )

    val indicatorColor =  remember(isSelected) { if (isSelected) selectedColor.copy(alpha = 0.16f) else Color.Transparent }
    val animatedIndicatorColor by animateColorAsState(
        targetValue = indicatorColor,
        animationSpec = tween(durationMillis = 250)
    )

    Column(
        modifier = modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(animatedIndicatorColor)
                .clickable(onClick = onTabSelected)
                .padding(horizontal = 20.dp, vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(item.iconResId),
                contentDescription = null,
                tint = animatedContentColor
            )
            Text(
                text = stringResource(item.textResId),
                style = MaterialTheme.typography.labelMedium,
                color = animatedContentColor
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    HowAreYouTheme {
        BottomNavigationBar(
            selectedTab = BottomTab.Statistic,
            tabs = BottomTab.default,
            onTabSelected = {}
        )
    }
}
