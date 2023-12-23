package com.github.michaelbull.advent2023.math

operator fun Int.rem(progression: IntProgression): Int {
    return ((this - progression.first).rem((progression.last - progression.first + 1))) + progression.first
}

fun Int.mod(progression: IntProgression): Int {
    return ((this - progression.first).mod(progression.last - progression.first + 1)) + progression.first
}
