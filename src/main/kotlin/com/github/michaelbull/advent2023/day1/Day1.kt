package com.github.michaelbull.advent2023.day1

import com.github.michaelbull.advent2023.Puzzle

private val STRING_TO_DIGIT = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

private val NUMERICAL_WORD = STRING_TO_DIGIT.keys
private val NUMERICAL_DIGIT = STRING_TO_DIGIT.values.map(Int::toString)
private val NUMERICAL_STRINGS = NUMERICAL_WORD + NUMERICAL_DIGIT

private fun String.toNumericCalibrationValue(): Int {
    val firstDigit = firstOrNull(Char::isDigit)?.digitToInt() ?: error("could not find first digit in $this")
    val secondDigit = lastOrNull(Char::isDigit)?.digitToInt() ?: error("could not find last digit in $this")
    return "$firstDigit$secondDigit".toInt()
}

private fun String.toLinguisticCalibrationValue(): Int {
    val (_, firstString) = findAnyOf(NUMERICAL_STRINGS) ?: error("could not find first digit in $this")
    val (_, lastString) = findLastAnyOf(NUMERICAL_STRINGS) ?: error("could not find last digit in $this")
    val firstDigit = firstString.toIntOrDigit()
    val secondDigit = lastString.toIntOrDigit()
    return "$firstDigit$secondDigit".toInt()
}

private fun String.toIntOrDigit(): Int {
    return toIntOrNull()
        ?: STRING_TO_DIGIT[this]
        ?: error("$this is not a number")
}

object Day1 : Puzzle<Sequence<String>, Int>(day = 1) {

    override fun parse(lines: Sequence<String>): Sequence<String> {
        return lines
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Sequence<String>): Int {
        return input.sumOf(String::toNumericCalibrationValue)
    }

    fun part2(input: Sequence<String>): Int {
        return input.sumOf(String::toLinguisticCalibrationValue)
    }
}
