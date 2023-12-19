package com.github.michaelbull.advent2023.day19

import com.github.michaelbull.advent2023.Puzzle

object Day19 : Puzzle<System, Long>(day = 19) {

    override fun parse(lines: Sequence<String>): System {
        return lines.toSystem()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: System): Long {
        return input.acceptedRatingTotal()
    }

    fun part2(input: System): Long {
        return input.distinctRatingCombinationCount(1..4000)
    }
}
