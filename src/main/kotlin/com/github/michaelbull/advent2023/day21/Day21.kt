package com.github.michaelbull.advent2023.day21

import com.github.michaelbull.advent2023.Puzzle

object Day21 : Puzzle<GardenMap, Long>(day = 21) {

    override fun parse(lines: Sequence<String>): GardenMap {
        return lines.toGardenMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: GardenMap): Long {
        return input.reachablePlots(64).toLong()
    }

    fun part2(input: GardenMap): Long {
        return input.infiniteReachablePlots(26501365)
    }
}
