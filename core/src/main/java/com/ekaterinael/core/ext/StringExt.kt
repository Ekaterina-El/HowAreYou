package com.ekaterinael.core.ext

fun String.capitalizeFirstLetter() = this.replaceFirstChar { it.uppercase() }