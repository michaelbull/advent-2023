package com.github.michaelbull.advent2023.day7

import com.github.michaelbull.advent2023.Puzzle

object Day7 : Puzzle<CamelCards, Long>(day = 7) {

    override fun parse(lines: Sequence<String>): CamelCards {
        return lines.toCamelCards()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: CamelCards): Long {
        return input.totalWinnings()
    }

    fun part2(input: CamelCards): Long {
        return input.jokerRule().totalWinnings()
    }
}
