package com.ekaterinael.mood_list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ekaterinael.core.ui.helper.getLocale
import com.ekaterinael.core.ui.navgiation.TopBarWithSearch
import com.ekaterinael.mood_list.MoodLogComponent

@Composable
fun MoodLog(component: MoodLogComponent) {
    val model by component.model.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 12.dp)
    ) {
        TopBarWithSearch(
            title = model.selectedMonthUserString(getLocale()),
            modifier = Modifier.padding(top = 20.dp, bottom = 14.dp),
            onClickForward = {},
            onClickSearch = {}
        )
        MoodLogsList(
            logs = model.logs,
            onClickAddNewLog = component::onClickAddNewLog,
            onSelectLog = component::onClickByLog
        )
    }
}