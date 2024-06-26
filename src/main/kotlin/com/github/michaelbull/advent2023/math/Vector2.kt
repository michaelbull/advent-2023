package com.github.michaelbull.advent2023.math

import kotlin.math.abs

fun Pair<Int, Int>.toVector2(): Vector2 {
    return Vector2(this)
}

data class Vector2(
    val x: Int = 0,
    val y: Int = 0,
) : Comparable<Vector2> {

    constructor(pair: Pair<Int, Int>) : this(
        x = pair.first,
        y = pair.second
    )

    operator fun unaryPlus(): Vector2 {
        return this
    }

    operator fun plus(amount: Int): Vector2 {
        return copy(
            x = this.x + amount,
            y = this.y + amount,
        )
    }

    operator fun plus(other: Vector2): Vector2 {
        return copy(
            x = this.x + other.x,
            y = this.y + other.y,
        )
    }

    operator fun unaryMinus(): Vector2 {
        return copy(
            x = -this.x,
            y = -this.y,
        )
    }

    operator fun minus(amount: Int): Vector2 {
        return copy(
            x = this.x - amount,
            y = this.y - amount,
        )
    }

    operator fun minus(other: Vector2): Vector2 {
        return copy(
            x = this.x - other.x,
            y = this.y - other.y,
        )
    }

    operator fun times(amount: Int): Vector2 {
        return copy(
            x = this.x * amount,
            y = this.y * amount,
        )
    }

    operator fun times(other: Vector2): Vector2 {
        return copy(
            x = this.x * other.x,
            y = this.y * other.y,
        )
    }

    operator fun div(amount: Int): Vector2 {
        return copy(
            x = this.x / amount,
            y = this.y / amount,
        )
    }

    operator fun div(other: Vector2): Vector2 {
        return copy(
            x = this.x / other.x,
            y = this.y / other.y,
        )
    }

    operator fun rem(amount: Int): Vector2 {
        return copy(
            x = this.x % amount,
            y = this.y % amount,
        )
    }

    operator fun rem(other: Vector2): Vector2 {
        return copy(
            x = this.x % other.x,
            y = this.y % other.y,
        )
    }

    operator fun rem(range: Vector2Range): Vector2 {
        return copy(
            x = this.x % range.xRange,
            y = this.y % range.yRange,
        )
    }

    infix fun cross(other: Vector2): Long {
        val a = this.x.toLong() * other.y.toLong()
        val b = this.y.toLong() * other.x.toLong()
        return a - b
    }

    infix fun dot(other: Vector2): Long {
        val a = this.x.toLong() * other.x.toLong()
        val b = this.y.toLong() * other.y.toLong()
        return a + b
    }

    fun abs(): Vector2 {
        return copy(
            x = abs(this.x),
            y = abs(this.y)
        )
    }

    fun coerceAtLeast(minimumValue: Vector2): Vector2 {
        return copy(
            x = x.coerceAtLeast(minimumValue.x),
            y = y.coerceAtLeast(minimumValue.y),
        )
    }

    fun coerceAtMost(maximumValue: Vector2): Vector2 {
        return copy(
            x = x.coerceAtMost(maximumValue.x),
            y = y.coerceAtMost(maximumValue.y),
        )
    }

    operator fun rangeTo(other: Vector2): Vector2Range {
        return Vector2Range(this, other)
    }

    override fun compareTo(other: Vector2): Int {
        return when {
            x < other.x -> -1
            x > other.x -> +1
            y < other.y -> -1
            y > other.y -> +1
            else -> 0
        }
    }

    override fun toString(): String {
        return "[$x, $y]"
    }

    companion object {
        val MIN_VALUE = Vector2(Int.MIN_VALUE, Int.MIN_VALUE)
        val MAX_VALUE = Vector2(Int.MAX_VALUE, Int.MAX_VALUE)

        val ZERO = Vector2(0, 0)
        val UP = Vector2(+1, +1)
        val DOWN = Vector2(-1, -1)

        val NORTH = Vector2(0, +1)
        val NORTH_EAST = Vector2(+1, +1)
        val EAST = Vector2(+1, 0)
        val SOUTH_EAST = Vector2(+1, -1)
        val SOUTH = Vector2(0, -1)
        val SOUTH_WEST = Vector2(-1, -1)
        val WEST = Vector2(-1, 0)
        val NORTH_WEST = Vector2(-1, +1)

        val CARDINAL_DIRECTIONS = setOf(
            NORTH,
            EAST,
            SOUTH,
            WEST
        )

        val ORDINAL_DIRECTIONS = setOf(
            NORTH_EAST,
            SOUTH_EAST,
            SOUTH_WEST,
            NORTH_WEST
        )

        val DIRECTIONS = CARDINAL_DIRECTIONS + ORDINAL_DIRECTIONS
    }
}
