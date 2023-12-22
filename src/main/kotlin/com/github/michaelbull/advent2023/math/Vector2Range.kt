package com.github.michaelbull.advent2023.math

import kotlin.math.abs

data class Vector2Range(
    override val start: Vector2,
    override val endInclusive: Vector2,
) : Iterable<Vector2>, ClosedRange<Vector2> {

    val isHorizontal: Boolean
        get() = start.x == endInclusive.x

    val isVertical: Boolean
        get() = start.y == endInclusive.y

    val isDiagonal: Boolean
        get() = abs(xDelta) == abs(yDelta)

    val xRange = start.x..endInclusive.x
    val yRange = start.y..endInclusive.y

    val xDelta = endInclusive.x - start.x
    val yDelta = endInclusive.y - start.y

    override fun iterator(): Iterator<Vector2> {
        return Vector2Iterator()
    }

    override fun contains(value: Vector2): Boolean {
        return value.x in xRange && value.y in yRange
    }

    override fun isEmpty(): Boolean {
        return start.x > endInclusive.x || start.y > endInclusive.y
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    fun coerceIn(range: Vector2Range): Vector2Range {
        val coercedStart = start.coerceAtLeast(range.start)
        val coercedEndInclusive = endInclusive.coerceAtMost(range.endInclusive)
        return coercedStart..coercedEndInclusive
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vector2Range) {
            if (isEmpty() && other.isEmpty()) {
                true
            } else if (start == other.start && endInclusive == other.endInclusive) {
                true
            } else {
                false
            }
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return if (isEmpty()) {
            -1
        } else {
            (31 * start.hashCode()) + endInclusive.hashCode()
        }
    }

    override fun toString(): String {
        return "$start..$endInclusive"
    }

    companion object {
        val EMPTY = Vector2Range(Vector2(1, 1), Vector2.ZERO)
    }

    private inner class Vector2Iterator : Iterator<Vector2> {
        private var finalElement = endInclusive
        private var hasNext = isNotEmpty()
        private var next = if (hasNext) start else finalElement

        override fun hasNext(): Boolean {
            return hasNext
        }

        override fun next(): Vector2 {
            val value = next

            if (value == finalElement) {
                if (hasNext) {
                    hasNext = false
                } else {
                    throw NoSuchElementException()
                }
            } else {
                next = next.step()
            }

            return value
        }

        private fun Vector2.step(): Vector2 {
            return when {
                x < endInclusive.x && y <= endInclusive.y -> copy(
                    x = x + 1,
                )

                x == endInclusive.x && y < endInclusive.y -> copy(
                    x = start.x,
                    y = y + 1,
                )

                x == endInclusive.x && y == endInclusive.y -> endInclusive

                else -> throw NoSuchElementException()
            }
        }
    }
}
