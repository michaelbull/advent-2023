package com.github.michaelbull.advent2023.day3

import com.github.michaelbull.advent2023.day3.Day3.part1
import com.github.michaelbull.advent2023.day3.Day3.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {


    @Test
    fun `example 1`() {
        assertEquals(4361, Day3.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(467835, Day3.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(532445, Day3.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(79842967, Day3.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...${'$'}.*....
            .664.598..
        """
    }
}
