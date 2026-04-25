package com.ekaterinael.utils

fun String.capitalizeFirstLetter(): String {
    return this.replaceFirstChar { it.uppercase() }
}