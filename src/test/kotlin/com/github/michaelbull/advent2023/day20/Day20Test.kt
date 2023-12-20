package com.github.michaelbull.advent2023.day20

import com.github.michaelbull.advent2023.day20.Day20.part1
import com.github.michaelbull.advent2023.day20.Day20.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Test {

    @Test
    fun `example 1`() {
        val input = """
            broadcaster -> a, b, c
            %a -> b
            %b -> c
            %c -> inv
            &inv -> a
        """

        assertEquals(32000000, Day20.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            broadcaster -> a
            %a -> inv, con
            &inv -> b
            %b -> con
            &con -> output
        """

        assertEquals(11687500, Day20.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(949764474, Day20.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(243221023462303, Day20.solve(::part2))
    }
}
