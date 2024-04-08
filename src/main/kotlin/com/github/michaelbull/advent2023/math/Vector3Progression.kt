package com.github.michaelbull.advent2023.math

infix fun Vector3.downTo(to: Vector3): Vector3Progression {
    return Vector3Progression.fromClosedRange(this, to, Vector3.DOWN)
}

infix fun Vector3Progression.step(step: Vector3): Vector3Progression {
    require(step.x >= 0) { "Step X must be non-negative, was: ${step.x}." }
    require(step.y >= 0) { "Step Y must be non-negative, was: ${step.y}." }
    require(step.z >= 0) { "Step Z must be non-negative, was: ${step.z}." }

    val progressionStep = Vector3(
        x = if (this.step.x > 0) step.x else -step.x,
        y = if (this.step.y > 0) step.y else -step.y,
        z = if (this.step.z > 0) step.z else -step.z
    )

    return Vector3Progression.fromClosedRange(first, last, progressionStep)
}

open class Vector3Progression(
    private val start: Vector3,
    private val endInclusive: Vector3,
    val step: Vector3,
) : Iterable<Vector3> {

    val first: Vector3 = start
    val last: Vector3

    private val isEmpty: Boolean

    init {
        require(step != Vector3.ZERO) { "Step must be non-zero." }

        val emptyX = isEmpty(Vector3::x)
        val emptyY = isEmpty(Vector3::y)
        val emptyZ = isEmpty(Vector3::z)

        isEmpty = emptyX || emptyY || emptyZ

        last = Vector3(
            x = if (emptyX) endInclusive.x else getLastElement(Vector3::x),
            y = if (emptyY) endInclusive.y else getLastElement(Vector3::y),
            z = if (emptyZ) endInclusive.z else getLastElement(Vector3::z),
        )
    }

    val xRange
        get() = first.x..last.x

    val yRange
        get() = first.y..last.y

    val zRange
        get() = first.z..last.z

    override fun iterator(): Iterator<Vector3> {
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

    private inline fun isEmpty(dimension: (Vector3) -> Int): Boolean = when {
        dimension(step) > 0 -> dimension(start) > dimension(endInclusive)
        dimension(step) < 0 -> dimension(start) < dimension(endInclusive)
        else -> if (dimension(start) == dimension(endInclusive)) {
            false
        } else {
            throw IllegalArgumentException("Start and end must be equal when step is zero.")
        }
    }

    private inline fun getLastElement(dimension: (Vector3) -> Int): Int {
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
        fun fromClosedRange(rangeStart: Vector3, rangeEnd: Vector3, step: Vector3): Vector3Progression {
            return Vector3Progression(rangeStart, rangeEnd, step)
        }
    }

    private object EmptyIterator : Iterator<Vector3> {
        override fun hasNext() = false
        override fun next() = throw NoSuchElementException()
    }

    private inner class ProgressionIterator : Iterator<Vector3> {
        private var hasNext: Boolean = true
        private var next = first

        override fun hasNext(): Boolean = hasNext

        override fun next(): Vector3 {
            val value = next

            if (value == last) {
                if (!hasNext) throw NoSuchElementException()
                hasNext = false
            } else {
                next = next.step()
            }

            return value
        }

        private fun Vector3.step() = when {
            x != last.x -> copy(
                x = x + step.x,
            )

            y != last.y -> copy(
                x = first.x,
                y = y + step.y,
            )

            z != last.z -> copy(
                x = first.x,
                y = first.y,
                z = z + step.z,
            )

            else -> throw NoSuchElementException()
        }
    }
}
