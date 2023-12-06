package com.github.michaelbull.advent2023.math

class Vector2CharMap(
    val width: Int,
    val height: Int,
    init: (Vector2) -> Char = { DEFAULT_VALUE }
) : Iterable<Pair<Vector2, Char>> {

    val xRange = 0..<width
    val yRange = 0..<height

    private val values = CharArray(width * height) { position ->
        val x = position % width
        val y = position / width
        init(Vector2(x, y))
    }

    operator fun set(position: Vector2, value: Char) {
        values[indexOf(position)] = value
    }

    operator fun set(x: Int, y: Int, value: Char) {
        values[indexOf(x, y)] = value
    }

    operator fun get(x: Int, y: Int): Char {
        return values[indexOf(x, y)]
    }

    operator fun get(position: Vector2): Char {
        return values[indexOf(position)]
    }

    fun getOrNull(position: Vector2): Char? {
        return if (position in this) {
            values[indexOf(position)]
        } else {
            null
        }
    }

    operator fun contains(position: Vector2): Boolean {
        return position.x in xRange && position.y in yRange
    }

    fun copy(width: Int = this.width, height: Int = this.height): Vector2CharMap {
        return Vector2CharMap(width, height) { (x, y) ->
            if (x !in xRange || y !in yRange) {
                DEFAULT_VALUE
            } else {
                this[x, y]
            }
        }
    }

    fun positionsIterator() = iterator {
        for (x in xRange) {
            for (y in yRange) {
                yield(Vector2(x, y))
            }
        }
    }

    fun positions(): Iterable<Vector2> {
        return Iterable(::positionsIterator)
    }

    override fun iterator(): Iterator<Pair<Vector2, Char>> {
        return iterator {
            for (x in xRange) {
                for (y in yRange) {
                    val index = indexOf(x, y)
                    val position = Vector2(x, y)
                    yield(position to values[index])
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vector2CharMap) {
            values.contentEquals(other.values)
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return values.contentHashCode()
    }

    private fun indexOf(position: Vector2): Int {
        return indexOf(position.x, position.y)
    }

    private fun indexOf(x: Int, y: Int): Int {
        requireInBounds(x, y)
        return (y * width) + x
    }

    private fun requireInBounds(x: Int, y: Int) {
        require(x in xRange) { "x must be in $xRange, but was $x" }
        require(y in yRange) { "y must be in $yRange, but was $y" }
    }

    private companion object {
        private const val DEFAULT_VALUE = ' '
    }
}
