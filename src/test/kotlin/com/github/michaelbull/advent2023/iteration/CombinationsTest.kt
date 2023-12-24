package com.github.michaelbull.advent2023.iteration

import kotlin.test.Test
import kotlin.test.assertEquals

class CombinationsTest {

    @Test
    fun `combination pairs`() {
        val actual = "ABCD".toList().combinationPairs().toList()

        val expected = listOf(
            Pair('A', 'B'),
            Pair('A', 'C'),
            Pair('A', 'D'),
            Pair('B', 'C'),
            Pair('B', 'D'),
            Pair('C', 'D'),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `combination triples`() {
        val actual = "ABCD".toList().combinationTriples().toList()

        val expected = listOf(
            Triple('A', 'B', 'C'),
            Triple('A', 'B', 'D'),
            Triple('A', 'C', 'D'),
            Triple('B', 'C', 'D'),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `combination lists`() {
        val actual = (0..3).toList().combinations(3).toList()

        val expected = listOf(
            listOf(0, 1, 2),
            listOf(0, 1, 3),
            listOf(0, 2, 3),
            listOf(1, 2, 3),
        )

        assertEquals(expected, actual)
    }
}
