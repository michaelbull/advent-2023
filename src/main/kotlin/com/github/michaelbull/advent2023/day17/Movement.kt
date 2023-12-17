package com.github.michaelbull.advent2023.day17

import com.github.michaelbull.advent2023.math.Vector2

data class Movement(
    val cityBlock: Vector2,
    val delta: Vector2,
) {

    fun turnLeft(cityBlock: Vector2): Movement {
        return Movement(cityBlock, Vector2(delta.y, -delta.x))
    }

    fun turnRight(cityBlock: Vector2): Movement {
        return Movement(cityBlock, Vector2(-delta.y, delta.x))
    }
}
