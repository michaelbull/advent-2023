package com.github.michaelbull.advent2023.day5

import com.github.michaelbull.advent2023.day5.Day5.part1
import com.github.michaelbull.advent2023.day5.Day5.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {

    @Test
    fun `example 1`() {
        assertEquals(35, Day5.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(46, Day5.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(26273516, Day5.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(34039469, Day5.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4
        """
    }
}
