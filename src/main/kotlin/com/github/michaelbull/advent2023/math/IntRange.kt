package com.github.michaelbull.advent2023.math

operator fun Int.rem(range: IntRange): Int {
    return ((this - range.first).rem((range.last - range.first + 1))) + range.first
}

fun Int.mod(range: IntRange): Int {
    return ((this - range.first).mod(range.last - range.first + 1)) + range.first
}
