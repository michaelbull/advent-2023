package com.github.michaelbull.advent2023.day11

import com.github.michaelbull.advent2023.Puzzle

object Day11 : Puzzle<Image, Long>(day = 11) {

    override fun parse(lines: Sequence<String>): Image {
        return lines.toImage()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Image): Long {
        return input.expand(2).sumShortestPathLengths()
    }

    fun part2(input: Image): Long {
        return input.expand(1000000).sumShortestPathLengths()
    }
}
