package com.github.michaelbull.advent2023.math

class Vector3Range(
    start: Vector3,
    endInclusive: Vector3,
) : Vector3Progression(start, endInclusive, Vector3.UP), ClosedRange<Vector3> {

    override val start: Vector3
        get() = first

    override val endInclusive: Vector3
        get() = last

    override fun contains(value: Vector3): Boolean {
        return value.x in xProgression
            && value.y in yProgression
            && value.z in zProgression
    }

    override fun isEmpty(): Boolean {
        return super<Vector3Progression>.isEmpty()
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vector3Range) {
            if (isEmpty() && other.isEmpty()) {
                true
            } else if (first == other.first && last == other.last) {
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
            (31 * first.hashCode()) + last.hashCode()
        }
    }

    override fun toString(): String {
        return "$first..$last"
    }

    companion object {
        val EMPTY = Vector3Range(Vector3.UP, Vector3.ZERO)
    }
}
