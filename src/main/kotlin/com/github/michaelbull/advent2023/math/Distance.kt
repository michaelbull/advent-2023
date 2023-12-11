package com.github.michaelbull.advent2023.math

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Calculates the two-dimensional [Euclidean distance](https://en.wikipedia.org/wiki/Euclidean_distance#Two_dimensions)
 * between [positions][Vector2] [p] and [q] in the [x][Vector2.x] and [y][Vector2.y] dimensions.
 */
fun euclideanDistance(p: Vector2, q: Vector2): Int {
    val deltaX = (q.x - p.x).toDouble()
    val deltaY = (q.y - p.y).toDouble()
    return sqrt(deltaX.pow(2) + deltaY.pow(2)).toInt()
}

fun Vector2.euclideanDistanceTo(other: Vector2): Int {
    return euclideanDistance(this, other)
}

/**
 * Calculates the two-dimensional [Chebyshev distance](https://en.wikipedia.org/wiki/Chebyshev_distance)
 * between [positions][Vector2] [p] and [q] in the [x][Vector2.x] and [y][Vector2.y] dimensions.
 */
fun chebyshevDistance(p: Vector2, q: Vector2): Int {
    val deltaX = q.x - p.x
    val deltaY = q.y - p.y
    return max(abs(deltaX), abs(deltaY))
}

fun Vector2.chebyshevDistanceTo(other: Vector2): Int {
    return chebyshevDistance(this, other)
}

/**
 * Calculates the two-dimensional [Manhattan distance](https://en.wikipedia.org/wiki/Taxicab_geometry)
 * between [positions][Vector2] [p] and [q] in the [x][Vector2.x] and [y][Vector2.y] dimensions.
 */
fun manhattanDistance(p: Vector2, q: Vector2): Int {
    val deltaX = p.x - q.x
    val deltaY = p.y - q.y
    return abs(deltaX) + abs(deltaY)
}

fun Vector2.manhattanDistanceTo(other: Vector2): Int {
    return manhattanDistance(this, other)
}
