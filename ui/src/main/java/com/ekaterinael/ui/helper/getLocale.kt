package com.ekaterinael.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import java.util.Locale

@Composable
fun getLocale(): Locale = LocalConfiguration.current.locales[0]
