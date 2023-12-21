package com.github.michaelbull.advent2023.day17

import com.github.michaelbull.advent2023.math.Vector2

infix fun Vector2.movingIn(direction: Vector2): Movement {
    return Movement(this, direction)
}

data class Movement(
    val cityBlock: Vector2,
    val direction: Vector2,
)
