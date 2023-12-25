package com.github.michaelbull.advent2023.iteration

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ProductTest {

    @Test
    fun `product pairs`() {
        val actual = Pair("ABCD".toList(), "EFG".toList()).product().toList()

        val expected = listOf(
            Pair('A', 'E'),
            Pair('A', 'F'),
            Pair('A', 'G'),
            Pair('B', 'E'),
            Pair('B', 'F'),
            Pair('B', 'G'),
            Pair('C', 'E'),
            Pair('C', 'F'),
            Pair('C', 'G'),
            Pair('D', 'E'),
            Pair('D', 'F'),
            Pair('D', 'G'),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `product triples`() {
        val actual = Triple("ABCD".toList(), "EFG".toList(), "HI".toList()).product().toList()

        val expected = listOf(
            Triple('A', 'E', 'H'),
            Triple('A', 'E', 'I'),
            Triple('A', 'F', 'H'),
            Triple('A', 'F', 'I'),
            Triple('A', 'G', 'H'),
            Triple('A', 'G', 'I'),
            Triple('B', 'E', 'H'),
            Triple('B', 'E', 'I'),
            Triple('B', 'F', 'H'),
            Triple('B', 'F', 'I'),
            Triple('B', 'G', 'H'),
            Triple('B', 'G', 'I'),
            Triple('C', 'E', 'H'),
            Triple('C', 'E', 'I'),
            Triple('C', 'F', 'H'),
            Triple('C', 'F', 'I'),
            Triple('C', 'G', 'H'),
            Triple('C', 'G', 'I'),
            Triple('D', 'E', 'H'),
            Triple('D', 'E', 'I'),
            Triple('D', 'F', 'H'),
            Triple('D', 'F', 'I'),
            Triple('D', 'G', 'H'),
            Triple('D', 'G', 'I'),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `product lists`() {
        val range = (0..1).toList()
        val actual = listOf(range, range, range).product().toList()

        val expected = listOf(
            listOf(0, 0, 0),
            listOf(0, 0, 1),
            listOf(0, 1, 0),
            listOf(0, 1, 1),
            listOf(1, 0, 0),
            listOf(1, 0, 1),
            listOf(1, 1, 0),
            listOf(1, 1, 1),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `empty product`() {
        val actual = Pair(emptyList<Nothing>(), emptyList<Nothing>()).product().toList()
        assertTrue(actual.isEmpty())
    }
}
