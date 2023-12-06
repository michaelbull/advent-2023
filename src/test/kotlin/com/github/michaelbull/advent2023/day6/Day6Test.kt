package com.github.michaelbull.advent2023.day6

import com.github.michaelbull.advent2023.day6.Day6.part1
import com.github.michaelbull.advent2023.day6.Day6.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {

    @Test
    fun `example 1`() {
        assertEquals(288, Day6.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(71503, Day6.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(1660968, Day6.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(26499773, Day6.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            Time:      7  15   30
            Distance:  9  40  200
        """
    }
}
