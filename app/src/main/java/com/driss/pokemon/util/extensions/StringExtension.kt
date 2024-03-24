package com.driss.pokemon.util.extensions

import java.util.Locale

internal fun String.capitalizeFirst(): String =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

internal fun String.formatCapitalize(): String = this.replace("-", " ").trimStart().capitalizeFirst()