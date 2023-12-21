package com.github.michaelbull.advent2023.day21

import com.github.michaelbull.advent2023.day21.Day21.part1
import com.github.michaelbull.advent2023.day21.Day21.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {

    @Test
    fun `answer 1`() {
        assertEquals(3858, Day21.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(636350496972143, Day21.solve(::part2))
    }
}
