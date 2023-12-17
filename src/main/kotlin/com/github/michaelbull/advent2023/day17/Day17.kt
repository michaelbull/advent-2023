package com.github.michaelbull.advent2023.day17

import com.github.michaelbull.advent2023.Puzzle

object Day17 : Puzzle<CityMap, Int>(day = 17) {

    override fun parse(lines: Sequence<String>): CityMap {
        return lines.toCityMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )


    fun part1(input: CityMap): Int {
        return input.minHeatLoss(1..3)
    }

    fun part2(input: CityMap): Int {
        return input.minHeatLoss(4..10)
    }
}
