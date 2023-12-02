package com.github.michaelbull.advent2023.day2

import com.github.michaelbull.advent2023.day2.Day2.part1
import com.github.michaelbull.advent2023.day2.Day2.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun parse() {
        assertEquals(EXAMPLE.trimIndent(), Day2.parse(EXAMPLE).joinToString("\n"))
    }

    @Test
    fun `example 1`() {

        assertEquals(8, Day2.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(2286, Day2.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(2551, Day2.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(62811, Day2.solve(::part2))
    }

    private companion object {
        val EXAMPLE = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """
    }
}
