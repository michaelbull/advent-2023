package com.github.michaelbull.advent2023.day13

import com.github.michaelbull.advent2023.day13.Day13.part1
import com.github.michaelbull.advent2023.day13.Day13.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    @Test
    fun `example 1`() {
        assertEquals(405, Day13.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(400, Day13.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(33520, Day13.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(34824, Day13.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.

            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """
    }
}
