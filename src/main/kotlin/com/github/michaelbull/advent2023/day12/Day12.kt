package com.github.michaelbull.advent2023.day12

import com.github.michaelbull.advent2023.Puzzle

object Day12 : Puzzle<Sequence<SpringRecord>, Long>(day = 12) {

    override fun parse(lines: Sequence<String>): Sequence<SpringRecord> {
        return lines.map(String::toSpringRecord)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Sequence<SpringRecord>): Long {
        val cache = arrangementCountCache()

        return input.sumOf { record ->
            record.arrangementCount(cache)
        }
    }

    fun part2(input: Sequence<SpringRecord>): Long {
        val cache = arrangementCountCache()

        return input.sumOf { record ->
            record.unfoldedArrangementCount(cache)
        }
    }
}
