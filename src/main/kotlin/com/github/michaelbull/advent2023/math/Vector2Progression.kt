package com.github.michaelbull.advent2023.math

infix fun Vector2.downTo(to: Vector2): Vector2Progression {
    return Vector2Progression.fromClosedRange(this, to, Vector2.DOWN)
}

infix fun Vector2Progression.step(step: Vector2): Vector2Progression {
    require(step.x >= 0) { "Step X must be non-negative, was: ${step.x}." }
    require(step.y >= 0) { "Step Y must be non-negative, was: ${step.y}." }

    val progressionStep = Vector2(
        x = if (this.step.x > 0) step.x else -step.x,
        y = if (this.step.y > 0) step.y else -step.y,
    )

    return Vector2Progression.fromClosedRange(first, last, progressionStep)
}

open class Vector2Progression(
    private val start: Vector2,
    private val endInclusive: Vector2,
    val step: Vector2,
) : Iterable<Vector2> {

    val first: Vector2 = start
    val last: Vector2

    private val isEmpty: Boolean

    init {
        require(step != Vector2.ZERO) { "Step must be non-zero." }

        val emptyX = isEmpty(Vector2::x)
        val emptyY = isEmpty(Vector2::y)

        isEmpty = emptyX || emptyY

        last = Vector2(
            x = if (emptyX) endInclusive.x else getLastElement(Vector2::x),
            y = if (emptyY) endInclusive.y else getLastElement(Vector2::y),
        )
    }

    val xRange
        get() = first.x..last.x

    val yRange
        get() = first.y..last.y

    override fun iterator(): Iterator<Vector2> {
        return if (isEmpty) {
            EmptyIterator
        } else {
            ProgressionIterator()
        }
    }

    open fun isEmpty(): Boolean {
        return isEmpty
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    private inline fun isEmpty(dimension: (Vector2) -> Int): Boolean = when {
        dimension(step) > 0 -> dimension(start) > dimension(endInclusive)
        dimension(step) < 0 -> dimension(start) < dimension(endInclusive)
        else -> if (dimension(start) == dimension(endInclusive)) {
            false
        } else {
            throw IllegalArgumentException("Start and end must be equal when step is zero.")
        }
    }

    private inline fun getLastElement(dimension: (Vector2) -> Int): Int {
        val start = dimension(this.start)
        val end = dimension(this.endInclusive)
        val step = dimension(this.step)

        return if (step == 0) {
            start
        } else {
            IntProgression.fromClosedRange(start, end, step).last
        }
    }

    companion object {
        fun fromClosedRange(rangeStart: Vector2, rangeEnd: Vector2, step: Vector2): Vector2Progression {
            return Vector2Progression(rangeStart, rangeEnd, step)
        }
    }

    private object EmptyIterator : Iterator<Vector2> {
        override fun hasNext() = false
        override fun next() = throw NoSuchElementException()
    }

    private inner class ProgressionIterator : Iterator<Vector2> {
        private var hasNext: Boolean = true
        private var next = first

        override fun hasNext(): Boolean = hasNext

        override fun next(): Vector2 {
            val value = next

            if (value == last) {
                if (!hasNext) throw NoSuchElementException()
                hasNext = false
            } else {
                next = next.step()
            }

            return value
        }

        private fun Vector2.step() = when {
            x != last.x -> copy(
                x = x + step.x,
            )

            y != last.y -> copy(
                x = first.x,
                y = y + step.y,
            )

            else -> throw NoSuchElementException()
        }
    }
}
