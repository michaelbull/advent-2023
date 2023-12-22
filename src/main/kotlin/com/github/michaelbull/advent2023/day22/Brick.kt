package com.github.michaelbull.advent2023.day22

import com.github.michaelbull.advent2023.math.Vector3
import com.github.michaelbull.advent2023.math.Vector3Range

typealias Brick = Set<Vector3>

private val VECTOR3_REGEX = "\\d+,\\d+,\\d+".toRegex()
private val VECTOR3_RANGE_REGEX = "($VECTOR3_REGEX)~($VECTOR3_REGEX)".toRegex()

fun String.toBrick(): Brick {
    val result = requireNotNull(VECTOR3_RANGE_REGEX.matchEntire(this)) {
        "$this must match $VECTOR3_RANGE_REGEX"
    }

    val (start, endInclusive) = result.destructured

    val range = Vector3Range(
        start = start.toVector3(),
        endInclusive = endInclusive.toVector3()
    )

    return range.toSet()
}

private fun String.toVector3(): Vector3 {
    val (x, y, z) = split(",", limit = 3).map(String::toInt)
    return Vector3(x, y, z)
}

