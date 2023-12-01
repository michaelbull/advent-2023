package com.github.michaelbull.advent2023.math

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Vector3RangeTest {

    @Test
    fun contains() {
        val range = Vector3(0, 0, 0)..Vector3(100, 100, 100)
        assertTrue(range.contains(Vector3(0, 0, 0)))
        assertTrue(range.contains(Vector3(100, 100, 100)))
        assertTrue(range.contains(Vector3(50, 50, 50)))
        assertFalse(range.contains(Vector3(-1, 0, 0)))
        assertFalse(range.contains(Vector3(0, -1, 0)))
        assertFalse(range.contains(Vector3(0, 0, -1)))
        assertFalse(range.contains(Vector3(101, 100, 100)))
        assertFalse(range.contains(Vector3(100, 101, 100)))
        assertFalse(range.contains(Vector3(100, 100, 101)))
    }

    @Test
    fun isEmpty() {
        assertTrue((Vector3(1, 0, 0)..Vector3(0, 0, 0)).isEmpty())
        assertTrue((Vector3(0, 1, 0)..Vector3(0, 0, 0)).isEmpty())
        assertTrue((Vector3(0, 0, 1)..Vector3(0, 0, 0)).isEmpty())
        assertTrue((Vector3(1, 1, 1)..Vector3(0, 0, 0)).isEmpty())
        assertFalse((Vector3(0, 0, 0)..Vector3(0, 0, 0)).isEmpty())
        assertFalse((Vector3(0, 0, 0)..Vector3(5, 0, 0)).isEmpty())
        assertFalse((Vector3(0, 0, 0)..Vector3(0, 5, 0)).isEmpty())
        assertFalse((Vector3(0, 0, 0)..Vector3(0, 0, 5)).isEmpty())
        assertFalse((Vector3(0, 0, 0)..Vector3(5, 5, 0)).isEmpty())
        assertFalse((Vector3(0, 0, 0)..Vector3(5, 0, 5)).isEmpty())
        assertFalse((Vector3(0, 0, 0)..Vector3(0, 5, 5)).isEmpty())
        assertFalse((Vector3(0, 0, 0)..Vector3(5, 5, 5)).isEmpty())
    }
}
