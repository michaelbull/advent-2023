package com.github.michaelbull.advent2023.day16

import com.github.michaelbull.advent2023.day16.Day16.part1
import com.github.michaelbull.advent2023.day16.Day16.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {

    @Test
    fun `example 1`() {
        assertEquals(46, Day16.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(51, Day16.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(7111, Day16.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(7831, Day16.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            .|...\....
            |.-.\.....
            .....|-...
            ........|.
            ..........
            .........\
            ..../.\\..
            .-.-/..|..
            .|....-|.\
            ..//.|....
        """
    }
}
