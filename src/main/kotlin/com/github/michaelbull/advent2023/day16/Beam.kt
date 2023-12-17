package com.github.michaelbull.advent2023.day16

import com.github.michaelbull.advent2023.math.Vector2

data class Beam(
    val position: Vector2,
    val direction: Direction,
) {

    operator fun invoke(direction: Direction): Beam {
        return copy(direction = direction)
    }

    fun travelForwards(): Beam {
        return copy(position = position + direction.vector)
    }
}
