package com.github.michaelbull.advent2023.day9

import com.github.michaelbull.advent2023.Puzzle

object Day9 : Puzzle<Sequence<History>, Int>(day = 9) {

    override fun parse(lines: Sequence<String>): Sequence<History> {
        return lines.map(String::toHistory)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Sequence<History>): Int {
        return input.sumOf(History::nextValue)
    }

    fun part2(input: Sequence<History>): Int {
        return input.sumOf(History::previousValue)
    }
}
