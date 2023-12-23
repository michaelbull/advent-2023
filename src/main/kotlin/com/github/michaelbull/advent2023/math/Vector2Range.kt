package com.github.michaelbull.advent2023.math

class Vector2Range(
    start: Vector2,
    endInclusive: Vector2,
) : Vector2Progression(start, endInclusive, Vector2.UP), ClosedRange<Vector2> {

    override val start: Vector2 get() = first
    override val endInclusive: Vector2 get() = last

    override fun contains(value: Vector2): Boolean {
        return value.x in xProgression && value.y in yProgression
    }

    override fun isEmpty(): Boolean {
        return super<Vector2Progression>.isEmpty()
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vector2Range) {
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
        val EMPTY = Vector2Range(Vector2.UP, Vector2.ZERO)
    }
}
