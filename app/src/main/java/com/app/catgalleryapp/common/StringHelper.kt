package com.app.catgalleryapp.common

fun String?.or(other: String): String = this ?: other

fun String?.orDefault(): String = this ?: "-"