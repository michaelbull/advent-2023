package com.github.michaelbull.advent2023.day4

import com.github.michaelbull.advent2023.day4.Day4.part1
import com.github.michaelbull.advent2023.day4.Day4.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day4Test {

    @Test
    fun `example 1`() {
        assertEquals(13, Day4.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(30, Day4.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(24175, Day4.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(18846301, Day4.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """
    }
}
