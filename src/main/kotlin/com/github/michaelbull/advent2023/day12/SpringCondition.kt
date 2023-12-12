package com.github.michaelbull.advent2023.day12

sealed interface SpringCondition

data object Operational : SpringCondition
data object Damaged : SpringCondition
data object Unknown : SpringCondition

fun Char.toSpringCondition(): SpringCondition {
    return when (this) {
        '.' -> Operational
        '#' -> Damaged
        '?' -> Unknown
        else -> throw IllegalArgumentException("$this is not a spring condition")
    }
}
