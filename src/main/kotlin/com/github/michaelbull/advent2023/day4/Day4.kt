package com.github.michaelbull.advent2023.day4

import com.github.michaelbull.advent2023.Puzzle

object Day4 : Puzzle<Sequence<Scratchcard>, Int>(day = 4) {

    override fun parse(lines: Sequence<String>): Sequence<Scratchcard> {
        return lines.map(String::toScratchCard)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Sequence<Scratchcard>): Int {
        return input.sumOf(Scratchcard::points)
    }

    fun part2(input: Sequence<Scratchcard>): Int {
        return input.toList()
            .toWinningCounts()
            .sum()
    }
}
