package com.github.michaelbull.advent2023.day22

import com.github.michaelbull.advent2023.Puzzle

object Day22 : Puzzle<BrickSnapshot, Int>(day = 22) {

    override fun parse(lines: Sequence<String>): BrickSnapshot {
        return lines.toBrickSnapshot()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: BrickSnapshot): Int {
        return input.disintegratableBrickCount()
    }

    fun part2(input: BrickSnapshot): Int {
        return input.fallenBrickCount()
    }
}
