package com.github.michaelbull.advent2023.math

infix fun Vector3.downTo(to: Vector3): Vector3Progression {
    return Vector3Progression.fromClosedRange(this, to, Vector3.DOWN)
}

infix fun Vector3Progression.step(step: Vector3): Vector3Progression {
    require(step.x > 0) { "Step X must be positive, was: ${step.x}." }
    require(step.y > 0) { "Step Y must be positive, was: ${step.y}." }
    require(step.z > 0) { "Step Z must be positive, was: ${step.z}." }

    val progressionStep = Vector3(
        x = if (this.step.x > 0) step.x else -step.x,
        y = if (this.step.y > 0) step.y else -step.y,
        z = if (this.step.z > 0) step.z else -step.z
    )

    return Vector3Progression.fromClosedRange(first, last, progressionStep)
}

open class Vector3Progression(
    val first: Vector3,
    val last: Vector3,
    val step: Vector3,
) : Iterable<Vector3> {

    val xProgression = IntProgression.fromClosedRange(first.x, last.x, step.x)
    val yProgression = IntProgression.fromClosedRange(first.y, last.y, step.y)
    val zProgression = IntProgression.fromClosedRange(first.z, last.z, step.z)

    override fun iterator(): Iterator<Vector3> {
        return Vector3ProgressionIterator(first, last, step)
    }

    open fun isEmpty(): Boolean {
        return xProgression.isEmpty() && yProgression.isEmpty() && zProgression.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vector3Progression) {
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
        return "x=$xProgression, y=$yProgression, z=$zProgression"
    }

    companion object {
        fun fromClosedRange(rangeStart: Vector3, rangeEnd: Vector3, step: Vector3): Vector3Progression {
            return Vector3Progression(rangeStart, rangeEnd, step)
        }
    }
}
