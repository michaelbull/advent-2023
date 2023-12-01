package com.github.michaelbull.advent2023.math

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Vector2RangeTest {

    @Test
    fun contains() {
        val range = Vector2(0, 0)..Vector2(5, 5)
        assertTrue(range.contains(Vector2(0, 0)))
        assertTrue(range.contains(Vector2(5, 5)))
        assertTrue(range.contains(Vector2(3, 3)))
        assertFalse(range.contains(Vector2(-1, 0)))
        assertFalse(range.contains(Vector2(0, -1)))
        assertFalse(range.contains(Vector2(6, 0)))
        assertFalse(range.contains(Vector2(0, 6)))
    }

    @Test
    fun isEmpty() {
        assertTrue((Vector2(1, 0)..Vector2(0, 0)).isEmpty())
        assertTrue((Vector2(0, 1)..Vector2(0, 0)).isEmpty())
        assertTrue((Vector2(1, 1)..Vector2(0, 0)).isEmpty())
        assertFalse((Vector2(0, 0)..Vector2(0, 0)).isEmpty())
        assertFalse((Vector2(0, 0)..Vector2(5, 0)).isEmpty())
        assertFalse((Vector2(0, 0)..Vector2(0, 5)).isEmpty())
        assertFalse((Vector2(0, 0)..Vector2(5, 5)).isEmpty())
    }
}
