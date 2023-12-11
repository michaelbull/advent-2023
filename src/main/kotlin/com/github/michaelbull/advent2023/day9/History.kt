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
        val extrapolated = accumulator + last()

        return if (allEqual()) {
            extrapolated
        } else {
            zipDifferences().extrapolate(extrapolated)
        }
    }

    private fun <T> Iterable<T>.allEqual(): Boolean {
        val first = first()
        return all { it == first }
    }

    private fun List<Int>.zipDifferences(): List<Int> {
        return zipWithNext(::difference)
    }

    private fun difference(a: Int, b: Int): Int {
        return b - a
    }
}
