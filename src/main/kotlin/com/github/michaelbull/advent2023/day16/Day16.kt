package com.github.michaelbull.advent2023.day16

import com.github.michaelbull.advent2023.Puzzle
import com.github.michaelbull.advent2023.math.Vector2

object Day16 : Puzzle<Contraption, Int>(day = 16) {

    override fun parse(lines: Sequence<String>): Contraption {
        return lines.toContraption()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Contraption): Int {
        val beam = Beam(
            position = Vector2(-1, 0),
            direction = Right
        )

        return input.energisedTileCount(beam)
    }

    fun part2(input: Contraption): Int {
        return input.maxEnergisedTileCount()
    }
}
