package com.github.michaelbull.advent2023.day8

import com.github.michaelbull.advent2023.day8.Day8.part1
import com.github.michaelbull.advent2023.day8.Day8.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {

    @Test
    fun `example 1`() {
        val input = """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """

        assertEquals(2, Day8.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """

        assertEquals(6, Day8.solve(::part1, input))
    }

    @Test
    fun `example 3`() {
        val input = """
            LR

            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """

        assertEquals(6, Day8.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(21389, Day8.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(21083806112641, Day8.solve(::part2))
    }
}
