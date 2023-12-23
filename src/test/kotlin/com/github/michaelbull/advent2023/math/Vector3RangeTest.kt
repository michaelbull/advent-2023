package com.github.michaelbull.advent2023.math

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Vector3RangeTest {

    @Test
    fun `contains min`() {
        val range = Vector3(0, 0, 0)..Vector3(100, 100, 100)
        assertTrue(range.contains(Vector3(0, 0, 0)))
    }

    @Test
    fun `contains max`() {
        val range = Vector3(0, 0, 0)..Vector3(100, 100, 100)
        assertTrue(range.contains(Vector3(100, 100, 100)))
    }

    @Test
    fun contains() {
        val range = Vector3(0, 0, 0)..Vector3(100, 100, 100)
        assertTrue(range.contains(Vector3(50, 50, 50)))
    }

    @Test
    fun `contains below x bounds`() {
        val range = Vector3(0, 0, 0)..Vector3(100, 100, 100)
        assertFalse(range.contains(Vector3(-1, 0, 0)))
    }

    @Test
    fun `contains above x bounds`() {
        val range = Vector3(0, 0, 0)..Vector3(100, 100, 100)
        assertFalse(range.contains(Vector3(101, 0, 0)))
    }

    @Test
    fun `contains below y bounds`() {
        val range = Vector3(0, 0, 0)..Vector3(100, 100, 100)
        assertFalse(range.contains(Vector3(0, -1, 0)))
    }

    @Test
    fun `contains above y bounds`() {
        val range = Vector3(0, 0, 0)..Vector3(100, 100, 100)
        assertFalse(range.contains(Vector3(0, 101, 0)))
    }

    @Test
    fun `contains below z bounds`() {
        val range = Vector3(0, 0, 0)..Vector3(100, 100, 100)
        assertFalse(range.contains(Vector3(0, 0, -1)))
    }

    @Test
    fun `contains above z bounds`() {
        val range = Vector3(0, 0, 0)..Vector3(100, 100, 100)
        assertFalse(range.contains(Vector3(0, 0, 101)))
    }

    @Test
    fun `isEmpty on empty x axis`() {
        val range = Vector3(1, 0, 0)..Vector3(0, 0, 0)
        assertFalse(range.isEmpty())
    }

    @Test
    fun `isEmpty on empty y axis`() {
        val range = Vector3(0, 1, 0)..Vector3(0, 0, 0)
        assertFalse(range.isEmpty())
    }

    @Test
    fun `isEmpty on empty z axis`() {
        val range = Vector3(0, 0, 1)..Vector3(0, 0, 0)
        assertFalse(range.isEmpty())
    }

    @Test
    fun `isEmpty on empty x y and z axis`() {
        val range = Vector3(1, 1, 1)..Vector3(0, 0, 0)
        assertTrue(range.isEmpty())
    }

    @Test
    fun `isEmpty on single-length x, y and z axis`() {
        val range = Vector3(0, 0, 0)..Vector3(0, 0, 0)
        assertFalse(range.isEmpty())
    }

    @Test
    fun `isEmpty on multi-length x axis`() {
        val range = Vector3(0, 0, 0)..Vector3(5, 0, 0)
        assertFalse(range.isEmpty())
    }

    @Test
    fun `isEmpty on multi-length y axis`() {
        val range = Vector3(0, 0, 0)..Vector3(0, 5, 0)
        assertFalse(range.isEmpty())
    }

    @Test
    fun `isEmpty on multi-length z axis`() {
        val range = Vector3(0, 0, 0)..Vector3(0, 0, 5)
        assertFalse(range.isEmpty())
    }

    @Test
    fun `isEmpty on multi-length x and y axis`() {
        val range = Vector3(0, 0, 0)..Vector3(5, 5, 0)
        assertFalse(range.isEmpty())
    }

    @Test
    fun `isEmpty on multi-length x and z axis`() {
        val range = Vector3(0, 0, 0)..Vector3(5, 0, 5)
        assertFalse(range.isEmpty())
    }

    @Test
    fun `isEmpty on multi-length y and z axis`() {
        val range = Vector3(0, 0, 0)..Vector3(0, 5, 5)
        assertFalse(range.isEmpty())
    }

    @Test
    fun `isEmpty on multi-length x, y, and z axis`() {
        val range = Vector3(0, 0, 0)..Vector3(5, 5, 5)
        assertFalse(range.isEmpty())
    }
}
