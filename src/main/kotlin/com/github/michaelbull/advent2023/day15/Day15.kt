package com.github.michaelbull.advent2023.day15

import com.github.michaelbull.advent2023.Puzzle

object Day15 : Puzzle<List<String>, Int>(day = 15) {

    override fun parse(lines: Sequence<String>): List<String> {
        return lines.first().split(",")
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: List<String>): Int {
        return input.hash()
    }

    fun part2(input: List<String>): Int {
        return input.toLensConfiguration().focusingPower()
    }
}
