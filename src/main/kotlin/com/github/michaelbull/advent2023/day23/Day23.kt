package com.github.michaelbull.advent2023.day23

import com.github.michaelbull.advent2023.Puzzle

object Day23 : Puzzle<HikingTrailMap, Int>(day = 23) {

    override fun parse(lines: Sequence<String>): HikingTrailMap {
        return lines.toHikingTrailMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: HikingTrailMap): Int {
        return input.longestHikeSteps()
    }

    fun part2(input: HikingTrailMap): Int {
        return input.levelledOutLongestHikeSteps()
    }
}
