package com.github.michaelbull.advent2023.day24

import com.github.michaelbull.advent2023.day24.Day24.part1
import kotlin.test.Test
import kotlin.test.assertEquals

class Day24Test {

    @Test
    fun `answer 1`() {
        assertEquals(13149, Day24.solve(::part1))
    }
}
