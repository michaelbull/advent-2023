package com.github.michaelbull.advent2023.math

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class Vector2BooleanMapTest {

    @Test
    fun `get within bounds`() {
        val map = Vector2BooleanMap(width = 10, height = 10) { (x, y) ->
            x == 3 && y == 6
        }

        assertTrue(map[Vector2(3, 6)])
        assertFalse(map[Vector2(2, 5)])
        assertFalse(map[Vector2(0, 0)])
        assertFalse(map[Vector2(9, 9)])
    }

    @Test
    fun `get below x bounds`() {
        val map = Vector2BooleanMap(width = 10, height = 20)

        val exception = assertFailsWith<IllegalArgumentException> {
            map[Vector2(-5, 0)]
        }

        assertEquals(exception.message, "x must be in 0..9, but was -5")
    }

    @Test
    fun `get above x bounds`() {
        val map = Vector2BooleanMap(width = 10, height = 20)

        val exception = assertFailsWith<IllegalArgumentException> {
            map[Vector2(15, 0)]
        }

        assertEquals(exception.message, "x must be in 0..9, but was 15")
    }

    @Test
    fun `get below y bounds`() {
        val map = Vector2BooleanMap(width = 10, height = 20)

        val exception = assertFailsWith<IllegalArgumentException> {
            map[Vector2(0, -7)]
        }

        assertEquals(exception.message, "y must be in 0..19, but was -7")
    }

    @Test
    fun `get above y bounds`() {
        val map = Vector2BooleanMap(width = 10, height = 20)

        val exception = assertFailsWith<IllegalArgumentException> {
            map[Vector2(0, 23)]
        }

        assertEquals(exception.message, "y must be in 0..19, but was 23")
    }

    @Test
    fun `getOrNull within bounds`() {
        val map = Vector2BooleanMap(width = 10, height = 20) { (x, _) ->
            (x % 2) == 0
        }

        assertEquals(true, map.getOrNull(Vector2(4, 6)))
        assertEquals(false, map.getOrNull(Vector2(3, 6)))
    }

    @Test
    fun `getOrNull below x bounds`() {
        val map = Vector2BooleanMap(width = 10, height = 20) { (x, _) ->
            (x % 2) == 0
        }

        assertNull(map.getOrNull(Vector2(-4, 6)))
    }

    @Test
    fun `getOrNull above x bounds`() {
        val map = Vector2BooleanMap(width = 10, height = 20) { (x, _) ->
            (x % 2) == 0
        }

        assertNull(map.getOrNull(Vector2(22, 6)))
    }

    @Test
    fun `getOrNull below y bounds`() {
        val map = Vector2BooleanMap(width = 10, height = 20) { (x, _) ->
            (x % 2) == 0
        }

        assertNull(map.getOrNull(Vector2(4, -5)))
    }

    @Test
    fun `getOrNull above y bounds`() {
        val map = Vector2BooleanMap(width = 10, height = 20) { (x, _) ->
            (x % 2) == 0
        }

        assertNull(map.getOrNull(Vector2(4, 28)))
    }
}
