package com.github.michaelbull.advent2023.math

operator fun Int.rem(range: IntRange): Int {
    return ((this - range.first) % (range.last - range.first + 1)) + range.first
}
