package com.github.michaelbull.advent2023.day5

import com.github.michaelbull.advent2023.Puzzle

object Day5 : Puzzle<Almanac, Long>(day = 5) {

    override fun parse(lines: Sequence<String>): Almanac {
        return lines.toAlmanac()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Almanac): Long {
        return input.seeds.minOf(input.maps::lookup)
    }

    fun part2(input: Almanac): Long {
        val reversed = input.reverse()
        val seedRanges = input.seeds.toRangePairs()

        return LOCATION_NUMBERS.first { locationNumber ->
            val seed = reversed.maps.lookup(locationNumber)
            seedRanges.any { seed in it }
        }
    }

    private val LOCATION_NUMBERS = 0L..Long.MAX_VALUE
}
