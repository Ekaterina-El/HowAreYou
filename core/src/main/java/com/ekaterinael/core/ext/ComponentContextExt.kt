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
package com.ekaterinael.core.ext

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/**
 * Creates a [CoroutineScope] bound to the lifecycle of this [ComponentContext].
 *
 * The scope uses [Dispatchers.Main] and a [SupervisorJob], allowing child coroutines to fail
 * independently. It is automatically canceled, when the component is destroyed.
 *
 * @return A lifecycle-aware coroutine scope for the component.
 */
fun ComponentContext.componentScope(): CoroutineScope =
  CoroutineScope(Dispatchers.Main.immediate + SupervisorJob()).apply {
    lifecycle.doOnDestroy { cancel() }
  }
