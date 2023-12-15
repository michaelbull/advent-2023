package com.github.michaelbull.advent2023.day15

import com.github.michaelbull.advent2023.Puzzle

object Day15 : Puzzle<String, Int>(day = 15) {

    override fun parse(lines: Sequence<String>): String {
        return lines.first()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: String): Int {
        return input.steps().hash()
    }

    fun part2(input: String): Int {
        return input.toLensConfiguration().focusingPower()
    }
}
