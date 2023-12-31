package com.github.michaelbull.advent2023.math

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Vector2ProgressionIteratorTest {

    @Test
    fun `positive step`() {
        val first = Vector2(0, 0)
        val last = Vector2(10, 10)
        val step = Vector2(3, 3)

        val progression = (first..last).step(step)
        val iterator = progression.iterator()

        assertTrue(iterator.hasNext())

        val expected = listOf(
            Vector2(0, 0),
            Vector2(3, 0),
            Vector2(6, 0),
            Vector2(9, 0),
            Vector2(0, 3),
            Vector2(3, 3),
            Vector2(6, 3),
            Vector2(9, 3),
            Vector2(0, 6),
            Vector2(3, 6),
            Vector2(6, 6),
            Vector2(9, 6),
            Vector2(0, 9),
            Vector2(3, 9),
            Vector2(6, 9),
            Vector2(9, 9),
        )

        for (vector in expected) {
            assertEquals(vector, iterator.next())
        }

        assertFalse(iterator.hasNext())
    }

    @Test
    fun `negative step`() {
        val first = Vector2(10, 10)
        val last = Vector2(0, 0)
        val step = Vector2(3, 3)

        val progression = (first downTo last).step(step)
        val iterator = progression.iterator()

        assertTrue(iterator.hasNext())

        val expected = listOf(
            Vector2(10, 10),
            Vector2(7, 10),
            Vector2(4, 10),
            Vector2(1, 10),
            Vector2(10, 7),
            Vector2(7, 7),
            Vector2(4, 7),
            Vector2(1, 7),
            Vector2(10, 4),
            Vector2(7, 4),
            Vector2(4, 4),
            Vector2(1, 4),
            Vector2(10, 1),
            Vector2(7, 1),
            Vector2(4, 1),
            Vector2(1, 1),
        )

        for (vector in expected) {
            assertEquals(vector, iterator.next())
        }
    }
}
