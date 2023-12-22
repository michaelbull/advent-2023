package com.github.michaelbull.advent2023.math

data class Vector3Range(
    override val start: Vector3,
    override val endInclusive: Vector3,
) : Iterable<Vector3>, ClosedRange<Vector3> {

    val xRange = start.x..endInclusive.x
    val yRange = start.y..endInclusive.y
    val zRange = start.z..endInclusive.z

    val xDelta = endInclusive.x - start.x
    val yDelta = endInclusive.y - start.y
    val zDelta = endInclusive.z - start.z

    fun coerceIn(range: Vector3Range): Vector3Range {
        val coercedStart = start.coerceAtLeast(range.start)
        val coercedEndInclusive = endInclusive.coerceAtMost(range.endInclusive)
        return coercedStart..coercedEndInclusive
    }

    override fun iterator(): Iterator<Vector3> {
        return Vector3Iterator()
    }

    override fun contains(value: Vector3): Boolean {
        return value.x in xRange && value.y in yRange && value.z in zRange
    }

    override fun isEmpty(): Boolean {
        return start.x > endInclusive.x || start.y > endInclusive.y || start.z > endInclusive.z
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vector3Range) {
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
        val EMPTY = Vector3Range(Vector3(1, 1, 1), Vector3.ZERO)
    }

    private inner class Vector3Iterator : Iterator<Vector3> {
        private var finalElement = endInclusive
        private var hasNext = isNotEmpty()
        private var next = if (hasNext) start else finalElement

        override fun hasNext(): Boolean {
            return hasNext
        }

        override fun next(): Vector3 {
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

        private fun Vector3.step(): Vector3 {
            return when {
                x < endInclusive.x && y <= endInclusive.y && z <= endInclusive.z -> copy(
                    x = x + 1,
                )

                x == endInclusive.x && y < endInclusive.y && z <= endInclusive.z -> copy(
                    x = start.x,
                    y = y + 1,
                )

                x == endInclusive.x && y == endInclusive.y && z < endInclusive.z -> copy(
                    x = start.x,
                    y = start.y,
                    z = z + 1,
                )

                x == endInclusive.x && y == endInclusive.y && z == endInclusive.z -> endInclusive

                else -> throw NoSuchElementException()
            }
        }
    }
}
