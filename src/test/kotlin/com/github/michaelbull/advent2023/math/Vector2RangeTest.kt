package com.github.michaelbull.advent2023.math

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Vector2RangeTest {

    @Test
    fun `contains min`() {
        val range = Vector2(0, 0)..Vector2(5, 5)
        assertTrue(range.contains(Vector2(0, 0)))
    }

    @Test
    fun `contains max`() {
        val range = Vector2(0, 0)..Vector2(5, 5)
        assertTrue(range.contains(Vector2(5, 5)))
    }

    @Test
    fun contains() {
        val range = Vector2(0, 0)..Vector2(5, 5)
        assertTrue(range.contains(Vector2(3, 3)))
    }

    @Test
    fun `contains below x bounds`() {
        val range = Vector2(0, 0)..Vector2(5, 5)
        assertFalse(range.contains(Vector2(-1, 0)))
    }

    @Test
    fun `contains above x bounds`() {
        val range = Vector2(0, 0)..Vector2(5, 5)
        assertFalse(range.contains(Vector2(6, 0)))
    }

    @Test
    fun `contains below y bounds`() {
        val range = Vector2(0, 0)..Vector2(5, 5)
        assertFalse(range.contains(Vector2(0, -1)))
    }

    @Test
    fun `contains above y bounds`() {
        val range = Vector2(0, 0)..Vector2(5, 5)
        assertFalse(range.contains(Vector2(0, 6)))
    }

    @Test
    fun `isEmpty on empty x axis`() {
        assertTrue((Vector2(1, 0)..Vector2(0, 0)).isEmpty())
    }

    @Test
    fun `isEmpty on empty y axis`() {
        assertTrue((Vector2(0, 1)..Vector2(0, 0)).isEmpty())
    }

    @Test
    fun `isEmpty on empty x and y axis`() {
        assertTrue((Vector2(1, 1)..Vector2(0, 0)).isEmpty())
    }

    @Test
    fun `isEmpty on single-length x axis`() {
        assertFalse((Vector2(0, 0)..Vector2(5, 0)).isEmpty())
    }

    @Test
    fun `isEmpty on single-length y axis`() {
        assertFalse((Vector2(0, 0)..Vector2(0, 5)).isEmpty())
    }

    @Test
    fun `isEmpty on single-length x and y axis`() {
        assertFalse((Vector2(0, 0)..Vector2(0, 0)).isEmpty())
    }

    @Test
    fun `isEmpty on multi-length x and y axis`() {
        assertFalse((Vector2(0, 0)..Vector2(5, 5)).isEmpty())
    }
}
