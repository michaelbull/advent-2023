package com.github.michaelbull.advent2023.day25

import com.github.michaelbull.advent2023.Puzzle

object Day25 : Puzzle<WiringDiagram, Int>(day = 25) {

    override fun parse(lines: Sequence<String>): WiringDiagram {
        return lines.toWiringDiagram()
    }

    override fun solutions() = listOf(
        ::part1,
    )

    fun part1(input: WiringDiagram): Int {
        val (first, second) = input.disconnectedGroups()
        return first.size * second.size
    }
}
