package com.github.michaelbull.advent2023.math

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Vector3IteratorTest {

    @Test
    fun `positive step in one dimension`() {
        val start = Vector3(0, 4, 8)
        val endInclusive = Vector3(11, 10, 16)
        val step = Vector3(2, 0, 0)

        val progression = (start..endInclusive).step(step)
        val iterator = progression.iterator()

        assertTrue(iterator.hasNext())

        val expected = listOf(
            Vector3(0, 4, 8),
            Vector3(2, 4, 8),
            Vector3(4, 4, 8),
            Vector3(6, 4, 8),
            Vector3(8, 4, 8),
            Vector3(10, 4, 8),
        )

        for (vector in expected) {
            assertEquals(vector, iterator.next())
        }

        assertFalse(iterator.hasNext())
    }

    @Test
    fun `positive step in two dimensions`() {
        val start = Vector3(0, 4, 8)
        val endInclusive = Vector3(11, 10, 16)
        val step = Vector3(2, 3, 0)

        val progression = (start..endInclusive).step(step)
        val iterator = progression.iterator()

        assertTrue(iterator.hasNext())

        val expected = listOf(
            Vector3(0, 4, 8),
            Vector3(2, 4, 8),
            Vector3(4, 4, 8),
            Vector3(6, 4, 8),
            Vector3(8, 4, 8),
            Vector3(10, 4, 8),
            Vector3(0, 7, 8),
            Vector3(2, 7, 8),
            Vector3(4, 7, 8),
            Vector3(6, 7, 8),
            Vector3(8, 7, 8),
            Vector3(10, 7, 8),
            Vector3(0, 10, 8),
            Vector3(2, 10, 8),
            Vector3(4, 10, 8),
            Vector3(6, 10, 8),
            Vector3(8, 10, 8),
            Vector3(10, 10, 8),
        )

        for (vector in expected) {
            assertEquals(vector, iterator.next())
        }

        assertFalse(iterator.hasNext())
    }

    @Test
    fun `positive step in three dimensions`() {
        val start = Vector3(0, 0, 0)
        val endInclusive = Vector3(10, 10, 10)
        val step = Vector3(3, 3, 3)

        val progression = (start..endInclusive).step(step)
        val iterator = progression.iterator()

        assertTrue(iterator.hasNext())

        val expected = listOf(
            Vector3(0, 0, 0),
            Vector3(3, 0, 0),
            Vector3(6, 0, 0),
            Vector3(9, 0, 0),
            Vector3(0, 3, 0),
            Vector3(3, 3, 0),
            Vector3(6, 3, 0),
            Vector3(9, 3, 0),
            Vector3(0, 6, 0),
            Vector3(3, 6, 0),
            Vector3(6, 6, 0),
            Vector3(9, 6, 0),
            Vector3(0, 9, 0),
            Vector3(3, 9, 0),
            Vector3(6, 9, 0),
            Vector3(9, 9, 0),
            Vector3(0, 0, 3),
            Vector3(3, 0, 3),
            Vector3(6, 0, 3),
            Vector3(9, 0, 3),
            Vector3(0, 3, 3),
            Vector3(3, 3, 3),
            Vector3(6, 3, 3),
            Vector3(9, 3, 3),
            Vector3(0, 6, 3),
            Vector3(3, 6, 3),
            Vector3(6, 6, 3),
            Vector3(9, 6, 3),
            Vector3(0, 9, 3),
            Vector3(3, 9, 3),
            Vector3(6, 9, 3),
            Vector3(9, 9, 3),
            Vector3(0, 0, 6),
            Vector3(3, 0, 6),
            Vector3(6, 0, 6),
            Vector3(9, 0, 6),
            Vector3(0, 3, 6),
            Vector3(3, 3, 6),
            Vector3(6, 3, 6),
            Vector3(9, 3, 6),
            Vector3(0, 6, 6),
            Vector3(3, 6, 6),
            Vector3(6, 6, 6),
            Vector3(9, 6, 6),
            Vector3(0, 9, 6),
            Vector3(3, 9, 6),
            Vector3(6, 9, 6),
            Vector3(9, 9, 6),
            Vector3(0, 0, 9),
            Vector3(3, 0, 9),
            Vector3(6, 0, 9),
            Vector3(9, 0, 9),
            Vector3(0, 3, 9),
            Vector3(3, 3, 9),
            Vector3(6, 3, 9),
            Vector3(9, 3, 9),
            Vector3(0, 6, 9),
            Vector3(3, 6, 9),
            Vector3(6, 6, 9),
            Vector3(9, 6, 9),
            Vector3(0, 9, 9),
            Vector3(3, 9, 9),
            Vector3(6, 9, 9),
            Vector3(9, 9, 9),
        )

        for (vector in expected) {
            assertEquals(vector, iterator.next())
        }

        assertFalse(iterator.hasNext())
    }

    @Test
    fun `negative step in one dimension`() {
        val start = Vector3(10, 10, 10)
        val endInclusive = Vector3(0, 0, 0)
        val step = Vector3(3, 0, 0)

        val progression = (start downTo endInclusive).step(step)
        val iterator = progression.iterator()

        assertTrue(iterator.hasNext())

        val expected = listOf(
            Vector3(10, 10, 10),
            Vector3(7, 10, 10),
            Vector3(4, 10, 10),
            Vector3(1, 10, 10),
        )

        for (vector in expected) {
            assertEquals(vector, iterator.next())
        }

        assertFalse(iterator.hasNext())
    }

    @Test
    fun `negative step in two dimensions`() {
        val start = Vector3(10, 10, 10)
        val endInclusive = Vector3(0, 0, 0)
        val step = Vector3(3, 3, 0)

        val progression = (start downTo endInclusive).step(step)
        val iterator = progression.iterator()

        assertTrue(iterator.hasNext())

        val expected = listOf(
            Vector3(10, 10, 10),
            Vector3(7, 10, 10),
            Vector3(4, 10, 10),
            Vector3(1, 10, 10),
            Vector3(10, 7, 10),
            Vector3(7, 7, 10),
            Vector3(4, 7, 10),
            Vector3(1, 7, 10),
            Vector3(10, 4, 10),
            Vector3(7, 4, 10),
            Vector3(4, 4, 10),
            Vector3(1, 4, 10),
            Vector3(10, 1, 10),
            Vector3(7, 1, 10),
            Vector3(4, 1, 10),
            Vector3(1, 1, 10),
        )

        for (vector in expected) {
            assertEquals(vector, iterator.next())
        }

        assertFalse(iterator.hasNext())
    }

    @Test
    fun `negative step in three dimensions`() {
        val start = Vector3(10, 10, 10)
        val endInclusive = Vector3(0, 0, 0)
        val step = Vector3(3, 3, 3)

        val progression = (start downTo endInclusive).step(step)
        val iterator = progression.iterator()

        assertTrue(iterator.hasNext())

        val expected = listOf(
            Vector3(10, 10, 10),
            Vector3(7, 10, 10),
            Vector3(4, 10, 10),
            Vector3(1, 10, 10),
            Vector3(10, 7, 10),
            Vector3(7, 7, 10),
            Vector3(4, 7, 10),
            Vector3(1, 7, 10),
            Vector3(10, 4, 10),
            Vector3(7, 4, 10),
            Vector3(4, 4, 10),
            Vector3(1, 4, 10),
            Vector3(10, 1, 10),
            Vector3(7, 1, 10),
            Vector3(4, 1, 10),
            Vector3(1, 1, 10),
            Vector3(10, 10, 7),
            Vector3(7, 10, 7),
            Vector3(4, 10, 7),
            Vector3(1, 10, 7),
            Vector3(10, 7, 7),
            Vector3(7, 7, 7),
            Vector3(4, 7, 7),
            Vector3(1, 7, 7),
            Vector3(10, 4, 7),
            Vector3(7, 4, 7),
            Vector3(4, 4, 7),
            Vector3(1, 4, 7),
            Vector3(10, 1, 7),
            Vector3(7, 1, 7),
            Vector3(4, 1, 7),
            Vector3(1, 1, 7),
            Vector3(10, 10, 4),
            Vector3(7, 10, 4),
            Vector3(4, 10, 4),
            Vector3(1, 10, 4),
            Vector3(10, 7, 4),
            Vector3(7, 7, 4),
            Vector3(4, 7, 4),
            Vector3(1, 7, 4),
            Vector3(10, 4, 4),
            Vector3(7, 4, 4),
            Vector3(4, 4, 4),
            Vector3(1, 4, 4),
            Vector3(10, 1, 4),
            Vector3(7, 1, 4),
            Vector3(4, 1, 4),
            Vector3(1, 1, 4),
            Vector3(10, 10, 1),
            Vector3(7, 10, 1),
            Vector3(4, 10, 1),
            Vector3(1, 10, 1),
            Vector3(10, 7, 1),
            Vector3(7, 7, 1),
            Vector3(4, 7, 1),
            Vector3(1, 7, 1),
            Vector3(10, 4, 1),
            Vector3(7, 4, 1),
            Vector3(4, 4, 1),
            Vector3(1, 4, 1),
            Vector3(10, 1, 1),
            Vector3(7, 1, 1),
            Vector3(4, 1, 1),
            Vector3(1, 1, 1),
        )

        for (vector in expected) {
            assertEquals(vector, iterator.next())
        }

        assertFalse(iterator.hasNext())
    }
}
