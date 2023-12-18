package com.github.michaelbull.advent2023.day18

import com.github.michaelbull.advent2023.math.Vector2

sealed interface Direction {
    val vector: Vector2
}

data object Up : Direction {
    override val vector = Vector2(+0, -1)
}

data object Down : Direction {
    override val vector = Vector2(+0, +1)
}

data object Left : Direction {
    override val vector = Vector2(-1, +0)
}

data object Right : Direction {
    override val vector = Vector2(+1, +0)
}

fun Char.toDirection(): Direction {
    return when (this) {
        'U', '3' -> Up
        'D', '1' -> Down
        'L', '2' -> Left
        'R', '0' -> Right
        else -> throw IllegalArgumentException("'$this' is not a direction")
    }
}
