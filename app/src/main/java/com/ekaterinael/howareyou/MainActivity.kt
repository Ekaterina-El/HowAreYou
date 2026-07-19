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
package com.ekaterinael.howareyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import javax.inject.Inject

/** Hosts the application's root UI and initializes the root Decompose component. */
class MainActivity : ComponentActivity() {
  /** Factory used to create the application's root component. */
  @Inject lateinit var defaultRootComponentFactory: DefaultRootComponent.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    (applicationContext as HowAreYouApp).applicationComponent.inject(this)

    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val root = defaultRootComponentFactory.create(componentContext = defaultComponentContext())

    setContent { RootContent(root) }
  }
}
