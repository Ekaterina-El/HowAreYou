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

/**
 * Returns this string with its first character converted to uppercase. If the string is empty, it
 * is returned unchanged.
 *
 * @return this string with an uppercase first character.
 */
fun String.capitalizeFirstLetter() = this.replaceFirstChar { it.uppercase() }
