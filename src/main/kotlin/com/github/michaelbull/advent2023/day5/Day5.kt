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
        return input.maps
            .lookup(input.seeds.toRangeList())
            .minOf(LongRange::start)
    }

    private fun List<Long>.toRangeList(): List<LongRange> {
        return chunked(2).map { (start, length) ->
            val end = start + length
            start..<end
        }
    }
}
