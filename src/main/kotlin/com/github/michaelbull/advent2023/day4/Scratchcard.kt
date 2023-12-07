package com.github.michaelbull.advent2023.day4

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

    val (_, id, winningNumbers, ownedNumbers) = result.groupValues

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
        return winningOwnedNumbers.fold(0) { points, _ ->
            if (points == 0) 1 else points * 2
        }
    }
}