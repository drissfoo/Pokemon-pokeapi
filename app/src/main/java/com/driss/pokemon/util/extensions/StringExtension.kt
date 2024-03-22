package com.driss.pokemon.util.extensions

import java.util.Locale

fun String.capitalFirst(): String =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

fun String.improve(): String = this.replace("-", " ").capitalFirst()