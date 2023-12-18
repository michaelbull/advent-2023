package com.github.michaelbull.advent2023.day18

import com.github.michaelbull.advent2023.day18.Day18.part1
import com.github.michaelbull.advent2023.day18.Day18.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Test {

    @Test
    fun `example 1`() {
        assertEquals(62, Day18.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(952408144115, Day18.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(35991, Day18.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(54058824661845, Day18.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            D 2 (#d2c081)
            R 2 (#59c680)
            D 2 (#411b91)
            L 5 (#8ceee2)
            U 2 (#caa173)
            L 1 (#1b58a2)
            U 2 (#caa171)
            R 2 (#7807d2)
            U 3 (#a77fa3)
            L 2 (#015232)
            U 2 (#7a21e3)
        """
    }
}
