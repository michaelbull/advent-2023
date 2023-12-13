package com.github.michaelbull.advent2023.day13

import com.github.michaelbull.advent2023.Puzzle

object Day13 : Puzzle<MirrorMap, Int>(day = 13) {

    override fun parse(lines: Sequence<String>): MirrorMap {
        return lines.toMirrorMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: MirrorMap): Int {
        return input.summarize(0)
    }

    fun part2(input: MirrorMap): Int {
        return input.summarize(1)
    }
}
