package com.github.michaelbull.advent2023.iteration

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PermutationsTest {

    @Test
    fun `permutation pairs`() {
        val actual = "ABCD".toList().permutationPairs().toList()

        val expected = listOf(
            Pair('A', 'B'),
            Pair('A', 'C'),
            Pair('A', 'D'),
            Pair('B', 'A'),
            Pair('B', 'C'),
            Pair('B', 'D'),
            Pair('C', 'A'),
            Pair('C', 'B'),
            Pair('C', 'D'),
            Pair('D', 'A'),
            Pair('D', 'B'),
            Pair('D', 'C'),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `permutation triples`() {
        val actual = "ABCD".toList().permutationTriples().toList()

        val expected = listOf(
            Triple('A', 'B', 'C'),
            Triple('A', 'B', 'D'),
            Triple('A', 'C', 'B'),
            Triple('A', 'C', 'D'),
            Triple('A', 'D', 'B'),
            Triple('A', 'D', 'C'),
            Triple('B', 'A', 'C'),
            Triple('B', 'A', 'D'),
            Triple('B', 'C', 'A'),
            Triple('B', 'C', 'D'),
            Triple('B', 'D', 'A'),
            Triple('B', 'D', 'C'),
            Triple('C', 'A', 'B'),
            Triple('C', 'A', 'D'),
            Triple('C', 'B', 'A'),
            Triple('C', 'B', 'D'),
            Triple('C', 'D', 'A'),
            Triple('C', 'D', 'B'),
            Triple('D', 'A', 'B'),
            Triple('D', 'A', 'C'),
            Triple('D', 'B', 'A'),
            Triple('D', 'B', 'C'),
            Triple('D', 'C', 'A'),
            Triple('D', 'C', 'B'),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `permutation lists`() {
        val actual = (0..2).toList().permutations().toList()

        val expected = listOf(
            listOf(0, 1, 2),
            listOf(0, 2, 1),
            listOf(1, 0, 2),
            listOf(1, 2, 0),
            listOf(2, 0, 1),
            listOf(2, 1, 0),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `empty permutations`() {
        val actual = emptyList<Nothing>().permutations().toList()
        assertTrue(actual.isEmpty())
    }

    @Test
    fun `excess permutations`() {
        val actual = "ABCD".toList().permutations(6).toList()
        assertTrue(actual.isEmpty())
    }
}
