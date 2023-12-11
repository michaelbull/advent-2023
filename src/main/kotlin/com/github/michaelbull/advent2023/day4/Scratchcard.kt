package com.github.michaelbull.advent2023.day4

import kotlin.math.pow

private val WHITESPACE_REGEX = "\\s+".toRegex()
private val CARD_REGEX = "Card$WHITESPACE_REGEX(\\d+): (.*) \\| (.*)".toRegex()

private fun String.toNumberSet(): Set<Int> {
    return trim()
        .split(WHITESPACE_REGEX)
        .map(String::toInt)
        .toSet()
}

fun String.toScratchCard(): Scratchcard {
    val result = requireNotNull(CARD_REGEX.matchEntire(this)) {
        "$this must match $CARD_REGEX"
    }

    val (id, winningNumbers, ownedNumbers) = result.destructured

    return Scratchcard(
        id = id.toInt(),
        winningNumbers = winningNumbers.toNumberSet(),
        ownedNumbers = ownedNumbers.toNumberSet(),
    )
}

fun Collection<Scratchcard>.toWinningCounts(): List<Int> {
    val counts = MutableList(size) { 1 }

    for ((index, scratchcard) in withIndex()) {
        repeat(scratchcard.winningCount) { offset ->
            counts[index + offset + 1] += counts[index]
        }
    }

    return counts
}

data class Scratchcard(
    val id: Int,
    val winningNumbers: Set<Int>,
    val ownedNumbers: Set<Int>
) {

    private val winningOwnedNumbers = ownedNumbers intersect winningNumbers

    val winningCount = winningOwnedNumbers.size

    fun points(): Int {
        return 2 pow winningCount - 1
    }

    private infix fun Int.pow(exponent: Int): Int {
        return toDouble().pow(exponent).toInt()
    }
}
