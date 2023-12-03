package com.github.michaelbull.advent2023.math

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class Vector2CharMapTest {

    @Test
    fun `get within bounds`() {
        val map = Vector2CharMap(width = 10, height = 10) { (x, y) ->
            if (x == 3 && y == 6) 'a' else 'b'
        }

        assertEquals('a', map[Vector2(3, 6)])
        assertEquals('b', map[Vector2(2, 5)])
        assertEquals('b', map[Vector2(0, 0)])
        assertEquals('b', map[Vector2(9, 9)])
    }

    @Test
    fun `get below x bounds`() {
        val map = Vector2CharMap(width = 10, height = 20)

        val exception = assertFailsWith<IllegalArgumentException> {
            map[Vector2(-5, 0)]
        }

        assertEquals(exception.message, "x must be in 0..9, but was -5")
    }

    @Test
    fun `get above x bounds`() {
        val map = Vector2CharMap(width = 10, height = 20)

        val exception = assertFailsWith<IllegalArgumentException> {
            map[Vector2(15, 0)]
        }

        assertEquals(exception.message, "x must be in 0..9, but was 15")
    }

    @Test
    fun `get below y bounds`() {
        val map = Vector2CharMap(width = 10, height = 20)

        val exception = assertFailsWith<IllegalArgumentException> {
            map[Vector2(0, -7)]
        }

        assertEquals(exception.message, "y must be in 0..19, but was -7")
    }

    @Test
    fun `get above y bounds`() {
        val map = Vector2CharMap(width = 10, height = 20)

        val exception = assertFailsWith<IllegalArgumentException> {
            map[Vector2(0, 23)]
        }

        assertEquals(exception.message, "y must be in 0..19, but was 23")
    }

    @Test
    fun `getOrNull within bounds`() {
        val map = Vector2CharMap(width = 10, height = 20) { (x, _) ->
            if ((x % 2) == 0) 'a' else 'b'
        }

        assertEquals('a', map.getOrNull(Vector2(4, 6)))
        assertEquals('b', map.getOrNull(Vector2(3, 6)))
    }

    @Test
    fun `getOrNull below x bounds`() {
        val map = Vector2CharMap(width = 10, height = 20) { (x, _) ->
            if ((x % 2) == 0) 'a' else 'b'
        }

        assertNull(map.getOrNull(Vector2(-4, 6)))
    }

    @Test
    fun `getOrNull above x bounds`() {
        val map = Vector2CharMap(width = 10, height = 20) { (x, _) ->
            if ((x % 2) == 0) 'a' else 'b'
        }

        assertNull(map.getOrNull(Vector2(22, 6)))
    }

    @Test
    fun `getOrNull below y bounds`() {
        val map = Vector2CharMap(width = 10, height = 20) { (x, _) ->
            if ((x % 2) == 0) 'a' else 'b'
        }

        assertNull(map.getOrNull(Vector2(4, -5)))
    }

    @Test
    fun `getOrNull above y bounds`() {
        val map = Vector2CharMap(width = 10, height = 20) { (x, _) ->
            if ((x % 2) == 0) 'a' else 'b'
        }

        assertNull(map.getOrNull(Vector2(4, 28)))
    }
}
