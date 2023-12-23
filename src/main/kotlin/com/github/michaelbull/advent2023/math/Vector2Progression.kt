package com.github.michaelbull.advent2023.math

infix fun Vector2.downTo(to: Vector2): Vector2Progression {
    return Vector2Progression.fromClosedRange(this, to, Vector2.DOWN)
}

infix fun Vector2Progression.step(step: Vector2): Vector2Progression {
    require(step.x > 0) { "Step X must be positive, was: ${step.x}." }
    require(step.y > 0) { "Step Y must be positive, was: ${step.y}." }

    val progressionStep = Vector2(
        x = if (this.step.x > 0) step.x else -step.x,
        y = if (this.step.y > 0) step.y else -step.y,
    )

    return Vector2Progression.fromClosedRange(first, last, progressionStep)
}

open class Vector2Progression(
    val first: Vector2,
    val last: Vector2,
    val step: Vector2,
) : Iterable<Vector2> {

    val xProgression = IntProgression.fromClosedRange(first.x, last.x, step.x)
    val yProgression = IntProgression.fromClosedRange(first.y, last.y, step.y)

    override fun iterator(): Iterator<Vector2> {
        return Vector2ProgressionIterator(first, last, step)
    }

    open fun isEmpty(): Boolean {
        return xProgression.isEmpty() && yProgression.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vector2Progression) {
            if (isEmpty() && other.isEmpty()) {
                true
            } else {
                first == other.first && last == other.last && step == other.step
            }
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return if (isEmpty()) {
            -1
        } else {
            (31 * (31 * first.hashCode() + last.hashCode()) + step.hashCode())
        }
    }

    override fun toString(): String {
        return "x=$xProgression, y=$yProgression"
    }

    companion object {
        fun fromClosedRange(rangeStart: Vector2, rangeEnd: Vector2, step: Vector2): Vector2Progression {
            return Vector2Progression(rangeStart, rangeEnd, step)
        }
    }
}
