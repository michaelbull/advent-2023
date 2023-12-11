package com.github.michaelbull.advent2023.day11

import com.github.michaelbull.advent2023.day11.Day11.part1
import com.github.michaelbull.advent2023.day11.Day11.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun `example 1`() {
        val input = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """

        assertEquals(374, Day11.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(9623138, Day11.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(726820169514, Day11.solve(::part2))
    }
}
