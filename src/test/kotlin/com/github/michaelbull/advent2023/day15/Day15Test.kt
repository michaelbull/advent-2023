package com.github.michaelbull.advent2023.day15

import com.github.michaelbull.advent2023.day15.Day15.part1
import com.github.michaelbull.advent2023.day15.Day15.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {

    @Test
    fun `example 1`() {
        assertEquals(1320, Day15.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(145, Day15.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(509784, Day15.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(230197, Day15.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
    }
}
