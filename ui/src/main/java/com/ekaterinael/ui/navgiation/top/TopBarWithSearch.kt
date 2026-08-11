/*
 * Copyright 2026 Ekaterina Elshina
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ekaterinael.ui.navgiation.top

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekaterinael.ui.buttons.AppIconButton
import com.ekaterinael.ui.theme.HowAreYouTheme
import java.util.Calendar
import java.util.Date

@Composable
fun TopBarWithSearch(
  selectedMonth: Date,
  modifier: Modifier = Modifier,
  onClickBack: (() -> Unit)? = null,
  onClickForward: (() -> Unit)? = null,
  onClickSearch: (() -> Unit)? = null,
) {
  var isMonthTitleAnimating by remember { mutableStateOf(false) }

  Row(
    modifier = Modifier.fillMaxWidth().then(modifier),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    AppIconButton(
      image = Icons.Default.ArrowBackIosNew,
      isActive = onClickBack != null,
      onClick = onClickBack.takeUnless { isMonthTitleAnimating },
    )
    Spacer(Modifier.width(5.dp))
    MonthTitle(
      modifier = Modifier.weight(1f),
      selectedMonth = selectedMonth,
      onAnimatingChanged = { isMonthTitleAnimating = it },
    )
    Spacer(Modifier.width(5.dp))
    AppIconButton(
      image = Icons.AutoMirrored.Default.ArrowForwardIos,
      isActive = onClickForward != null,
      onClick = onClickForward.takeUnless { isMonthTitleAnimating },
    )

    // TODO: add search feature
    if (HAS_SEARCH_FEATURE) {
      Spacer(Modifier.width(12.dp))
      AppIconButton(image = Icons.Outlined.Search, innerPadding = 6.dp, onClick = onClickSearch)
    }
  }
}

private const val HAS_SEARCH_FEATURE = false

@Preview
@Composable
private fun TopBarWithSearchPreview() {
  HowAreYouTheme {
    Box(Modifier.background(MaterialTheme.colorScheme.background)) {
      TopBarWithSearch(
        selectedMonth = Calendar.getInstance().time,
        onClickSearch = {},
        onClickForward = {},
      )
    }
  }
}
