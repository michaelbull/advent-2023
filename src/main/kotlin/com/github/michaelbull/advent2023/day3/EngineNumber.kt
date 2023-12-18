package com.github.michaelbull.advent2023.day3

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.chebyshevDistanceTo

fun Int.inEngineAt(position: Vector2): EngineNumber {
    return EngineNumber(position, this)
}

data class EngineNumber(
    override val position: Vector2,
    val value: Int,
) : EngineItem {

    private val length = value.toString().length

    private val positions = List(length) { deltaX ->
        Vector2(position.x + deltaX, position.y)
    }

    infix fun adjacentTo(other: Vector2): Boolean {
        return positions.any { position ->
            position adjacentTo other
        }
    }

    private infix fun Vector2.adjacentTo(other: Vector2): Boolean {
        return chebyshevDistanceTo(other) <= 1
    }
}
