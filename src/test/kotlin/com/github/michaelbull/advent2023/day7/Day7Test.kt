package com.github.michaelbull.advent2023.day7

import com.github.michaelbull.advent2023.day7.Day7.part1
import com.github.michaelbull.advent2023.day7.Day7.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {

    @Test
    fun `example 1`() {
        assertEquals(6440, Day7.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(5905, Day7.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(250957639, Day7.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(251515496, Day7.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """
    }
}
