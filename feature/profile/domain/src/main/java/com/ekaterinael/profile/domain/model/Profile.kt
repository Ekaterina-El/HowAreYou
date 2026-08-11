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
package com.ekaterinael.profile.domain.model

/**
 * Represents the current user's profile.
 *
 * @property firstName the user's first name.
 * @property lastName the user's last name.
 * @property photoUrl the URL of the user's profile photo, or `null` if not set.
 */
data class Profile(val firstName: String, val lastName: String, val photoUrl: String?)
