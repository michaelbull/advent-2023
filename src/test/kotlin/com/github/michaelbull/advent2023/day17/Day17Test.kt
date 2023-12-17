package com.github.michaelbull.advent2023.day17

import com.github.michaelbull.advent2023.day17.Day17.part1
import com.github.michaelbull.advent2023.day17.Day17.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {

    @Test
    fun `example 1`() {
        val input = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
        """

        assertEquals(102, Day17.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
        """

        assertEquals(94, Day17.solve(::part2, input))
    }

    @Test
    fun `example 3`() {
        val input = """
            111111111111
            999999999991
            999999999991
            999999999991
            999999999991
        """

        assertEquals(71, Day17.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(1001, Day17.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(1197, Day17.solve(::part2))
    }
}
