package com.github.michaelbull.advent2023.day9

fun String.toHistory(): History {
    return History(split(" ").map(String::toInt))
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

    private tailrec fun List<Int>.extrapolate(accumulator: Int = 0): Int {
        return if (allZero()) {
            accumulator
        } else {
            zipDifferences().extrapolate(accumulator + last())
        }
    }

    private fun List<Int>.allZero(): Boolean {
        return all { it == 0 }
    }

    private fun List<Int>.zipDifferences(): List<Int> {
        return zipWithNext { a, b -> b - a }
    }
}
