package com.github.michaelbull.advent2023.day9

import com.github.michaelbull.advent2023.day9.Day9.part1
import com.github.michaelbull.advent2023.day9.Day9.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {

    @Test
    fun `example 1`() {
        assertEquals(114, Day9.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(2, Day9.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(2038472161, Day9.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(1091, Day9.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """
    }
}
