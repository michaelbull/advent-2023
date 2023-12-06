package com.github.michaelbull.advent2023.day6

import com.github.michaelbull.advent2023.Puzzle

object Day6 : Puzzle<Sequence<String>, Int>(day = 6) {

    override fun parse(lines: Sequence<String>): Sequence<String> {
        return lines
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Sequence<String>): Int {
        return input.toRaces()
            .map(Race::winningCount)
            .reduce(Int::times)
    }

    fun part2(input: Sequence<String>): Int {
        return input.toRace()
            .winningCount()
    }
}
