package com.github.michaelbull.advent2023.day8

import com.github.michaelbull.advent2023.Puzzle

object Day8 : Puzzle<DesertMap, Long>(day = 8) {

    override fun parse(lines: Sequence<String>): DesertMap {
        return lines.toDesertMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: DesertMap): Long {
        return input.stepCount(
            start = Node("AAA"),
            end = Node("ZZZ")
        )
    }

    fun part2(input: DesertMap): Long {
        return input.stepCount(
            isStart = { (label) -> label.endsWith("A") },
            isEnd = { (label) -> label.endsWith("Z") }
        )
    }
}
