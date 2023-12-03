package com.github.michaelbull.advent2023.day3

import com.github.michaelbull.advent2023.Puzzle

object Day3 : Puzzle<EngineSchematic, Int>(day = 3) {

    override fun parse(lines: Sequence<String>): EngineSchematic {
        return lines.toEngineSchematic()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: EngineSchematic): Int {
        return input.partNumbers().sum()
    }

    fun part2(input: EngineSchematic): Int {
        return input.gearRatios().sum()
    }
}
