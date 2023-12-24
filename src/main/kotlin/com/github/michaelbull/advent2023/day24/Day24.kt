package com.github.michaelbull.advent2023.day24

import com.github.michaelbull.advent2023.Puzzle

object Day24 : Puzzle<HailstoneMap, Int>(day = 24) {

    override fun parse(lines: Sequence<String>): HailstoneMap {
        return lines.toHailstoneMap()
    }

    override fun solutions() = listOf(
        ::part1,
    )

    fun part1(input: HailstoneMap): Int {
        return input.intersections(200000000000000.0..400000000000000.0)
    }
}
