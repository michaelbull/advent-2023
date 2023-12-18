package com.github.michaelbull.advent2023.day18

import com.github.michaelbull.advent2023.Puzzle

object Day18 : Puzzle<DigPlan, Long>(day = 18) {

    override fun parse(lines: Sequence<String>): DigPlan {
        return lines.toDigPlan()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: DigPlan): Long {
        return input.lagoonCapacity()
    }

    fun part2(input: DigPlan): Long {
        return input.fix().lagoonCapacity()
    }
}
