package com.driss.pokemon.util.extensions

/**
 * Get value contained in this interval [[0,1]]
 */
@Suppress("MagicNumber")
internal fun Int.formatPercentage(): Float = toFloat().coerceAtLeast(0f).coerceAtMost(100f) / 100f