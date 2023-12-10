package com.github.michaelbull.advent2023.day10

import com.github.michaelbull.advent2023.Puzzle

object Day10 : Puzzle<PipeMap, Int>(day = 10) {

    override fun parse(lines: Sequence<String>): PipeMap {
        return lines.toPipeMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: PipeMap): Int {
        return input.farthestStepCount()
    }

    fun part2(input: PipeMap): Int {
        return input.enclosedTileCount()
    }
}
