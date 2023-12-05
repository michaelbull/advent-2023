package com.github.michaelbull.advent2023.math

import java.util.*

class Vector2BooleanMap(
    val width: Int,
    val height: Int,
    init: (Vector2) -> Boolean = { DEFAULT_VALUE }
) : Iterable<Vector2> {

    val xRange = 0..<width
    val yRange = 0..<height

    private val values = BitSet(width * height)

    init {
        for (x in xRange) {
            for (y in yRange) {
                val position = Vector2(x, y)
                set(position, init(position))
            }
        }
    }

    operator fun set(position: Vector2, value: Boolean) {
        values[indexOf(position)] = value
    }

    operator fun get(position: Vector2): Boolean {
        return values[indexOf(position)]
    }

    fun getOrNull(position: Vector2): Boolean? {
        return if (position in this) {
            values[indexOf(position)]
        } else {
            null
        }
    }

    operator fun contains(position: Vector2): Boolean {
        return position.x in xRange && position.y in yRange
    }

    private fun indexOf(position: Vector2): Int {
        return indexOf(position.x, position.y)
    }

    private fun indexOf(x: Int, y: Int): Int {
        check(x, y)
        return (y * width) + x
    }

    private fun check(x: Int, y: Int) {
        require(x in xRange) { "x must be in $xRange, but was $x" }
        require(y in yRange) { "y must be in $yRange, but was $y" }
    }

    override fun iterator() = iterator {
        for (x in xRange) {
            for (y in yRange) {
                yield(Vector2(x, y))
            }
        }
    }

    private companion object {
        private const val DEFAULT_VALUE = false
    }
}
