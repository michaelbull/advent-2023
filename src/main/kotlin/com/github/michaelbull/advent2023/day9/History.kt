package com.github.michaelbull.advent2023.day9

private val NUMBER_REGEX = "-?\\d+".toRegex()

fun String.toHistory(): History {
    val values = NUMBER_REGEX.findAll(this)
        .map { it.value.toInt() }
        .toList()

    return History(
        values = values
    )
}

data class History(
    val values: List<Int>
) {

    fun nextValue(): Int {
        return values.extrapolate()
    }

    fun previousValue(): Int {
        return values.reversed().extrapolate()
    }

    private fun List<Int>.extrapolate(): Int {
        return if (allZero()) {
            0
        } else {
            last() + zipDifferences().extrapolate()
        }
    }

    private fun List<Int>.allZero(): Boolean {
        return all { it == 0 }
    }

    private fun List<Int>.zipDifferences(): List<Int> {
        return zipWithNext { a, b -> b - a }
    }
}
