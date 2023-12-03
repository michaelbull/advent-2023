package com.github.michaelbull.advent2023.math

class Vector2IntMap(
    val width: Int,
    val height: Int,
    init: (Vector2) -> Int = { DEFAULT_VALUE }
) : Iterable<Vector2> {

    val xRange = 0 until width
    val yRange = 0 until height

    private val values = IntArray(width * height) { position ->
        val x = position % width
        val y = position / width
        init(Vector2(x, y))
    }

    operator fun set(position: Vector2, value: Int) {
        values[indexOf(position)] = value
    }

    operator fun set(x: Int, y: Int, value: Int) {
        values[indexOf(x, y)] = value
    }

    operator fun get(x: Int, y: Int): Int {
        return values[indexOf(x, y)]
    }

    operator fun get(position: Vector2): Int {
        return values[indexOf(position)]
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

    fun copy(width: Int = this.width, height: Int = this.height): Vector2IntMap {
        return Vector2IntMap(width, height) { (x, y) ->
            if (x !in xRange || y !in yRange) {
                DEFAULT_VALUE
            } else {
                this[x, y]
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vector2IntMap) {
            values.contentEquals(other.values)
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return values.contentHashCode()
    }

    override fun iterator() = iterator {
        for (x in xRange) {
            for (y in yRange) {
                yield(Vector2(x, y))
            }
        }
    }

    private companion object {
        private const val DEFAULT_VALUE = 0
    }
}
