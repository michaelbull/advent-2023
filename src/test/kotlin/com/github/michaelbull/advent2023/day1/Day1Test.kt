package com.github.michaelbull.advent2023.day1

import com.github.michaelbull.advent2023.day1.Day1.part1
import com.github.michaelbull.advent2023.day1.Day1.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun `example 1`() {
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """

        assertEquals(142, Day1.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """

        assertEquals(281, Day1.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(54304, Day1.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(54418, Day1.solve(::part2))
    }
}
