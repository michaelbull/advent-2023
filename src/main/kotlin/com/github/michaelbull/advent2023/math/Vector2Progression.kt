package com.github.michaelbull.advent2023.math

infix fun Vector2.downTo(to: Vector2): Vector2Progression {
    return Vector2Progression.fromClosedRange(this, to, Vector2.DOWN)
}

infix fun Vector2Progression.step(step: Vector2): Vector2Progression {
    require(step.x >= 0) { "Step X must be non-negative, was: ${step.x}." }
    require(step.y >= 0) { "Step Y must be non-negative, was: ${step.y}." }

    val progressionStep = Vector2(
        x = if (this.xProgression.step > 0) step.x else -step.x,
        y = if (this.yProgression.step > 0) step.y else -step.y,
    )

    return Vector2Progression.fromClosedRange(first, last, progressionStep)
}

private fun dimensionProgression(rangeStart: Int, rangeEnd: Int, step: Int): IntProgression {
    return if (step == 0) {
        IntRange.EMPTY
    } else {
        IntProgression.fromClosedRange(rangeStart, rangeEnd, step)
    }
}

open class Vector2Progression(
    start: Vector2,
    endInclusive: Vector2,
    step: Vector2,
) : Iterable<Vector2> {

    init {
        require(step != Vector2.ZERO) { "Step must be non-zero." }
    }

    val xProgression = dimensionProgression(start.x, endInclusive.x, step.x)
    val yProgression = dimensionProgression(start.y, endInclusive.y, step.y)

    val first: Vector2 = Vector2(
        x = if (xProgression.isEmpty()) start.x else xProgression.first,
        y = if (yProgression.isEmpty()) start.y else yProgression.first,
    )

    val last: Vector2 = Vector2(
        x = if (xProgression.isEmpty()) start.x else xProgression.last,
        y = if (yProgression.isEmpty()) start.y else yProgression.last,
    )

    override fun iterator(): Iterator<Vector2> {
        return Vector2Iterator()
    }

    open fun isEmpty(): Boolean {
        return xProgression.isEmpty()
            && yProgression.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vector2Progression) {
            return xProgression == other.xProgression
                && yProgression == other.yProgression
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = xProgression.hashCode()
        result = 31 * result + yProgression.hashCode()
        return result
    }

    override fun toString(): String {
        return "x=$xProgression, y=$yProgression"
    }

    companion object {
        fun fromClosedRange(rangeStart: Vector2, rangeEnd: Vector2, step: Vector2): Vector2Progression {
            return Vector2Progression(rangeStart, rangeEnd, step)
        }
    }

    private inner class Vector2Iterator : Iterator<Vector2> {
        private var xIterator = xProgression.iterator()
        private var yIterator = yProgression.iterator()

        private var hasNext: Boolean = !xProgression.isEmpty() || !yProgression.isEmpty()

        private var next = if (hasNext) {
            Vector2(
                x = if (xProgression.isEmpty()) first.x else xIterator.nextInt(),
                y = if (yProgression.isEmpty()) first.y else yIterator.nextInt(),
            )
        } else {
            last
        }

        override fun hasNext(): Boolean = hasNext

        override fun next(): Vector2 {
            val value = next

            if (value == last) {
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

        private fun Vector2.step() = when {
            xIterator.hasNext() -> {
                copy(
                    x = xIterator.nextInt()
                )
            }

            yIterator.hasNext() -> when {
                xProgression.isEmpty() -> {
                    copy(
                        y = yIterator.nextInt()
                    )
                }

                else -> {
                    xIterator = xProgression.iterator()

                    copy(
                        x = xIterator.nextInt(),
                        y = yIterator.nextInt(),
                    )
                }
            }

            else -> throw NoSuchElementException()
        }
    }
}
