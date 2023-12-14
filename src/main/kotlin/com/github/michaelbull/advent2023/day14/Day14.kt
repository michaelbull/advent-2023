package com.github.michaelbull.advent2023.day14

import com.github.michaelbull.advent2023.Puzzle

object Day14 : Puzzle<Platform, Int>(day = 14) {

    override fun parse(lines: Sequence<String>): Platform {
        return lines.toPlatform()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Platform): Int {
        return input
            .tiltNorth()
            .totalLoad()
    }

    fun part2(input: Platform): Int {
        return input
            .cycle(1000000000)
            .last()
            .totalLoad()
    }
}
