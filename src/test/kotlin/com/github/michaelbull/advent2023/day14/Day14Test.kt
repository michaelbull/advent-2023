package com.github.michaelbull.advent2023.day14

import com.github.michaelbull.advent2023.day14.Day14.part1
import com.github.michaelbull.advent2023.day14.Day14.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    @Test
    fun `example 1`() {
        assertEquals(136, Day14.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(64, Day14.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(108759, Day14.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(89089, Day14.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """
    }
}
