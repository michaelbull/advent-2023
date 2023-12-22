package com.github.michaelbull.advent2023.day22

import com.github.michaelbull.advent2023.day22.Day22.part1
import com.github.michaelbull.advent2023.day22.Day22.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Test {

    @Test
    fun `example 1`() {
        assertEquals(5, Day22.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(7, Day22.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(424, Day22.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(55483, Day22.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            1,0,1~1,2,1
            0,0,2~2,0,2
            0,2,3~2,2,3
            0,0,4~0,2,4
            2,0,5~2,2,5
            0,1,6~2,1,6
            1,1,8~1,1,9
        """
    }
}
