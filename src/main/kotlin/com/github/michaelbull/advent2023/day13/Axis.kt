package com.github.michaelbull.advent2023.day13

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.Vector2CharMap

interface Axis {
    val range: IntRange
    val opposite: IntRange
    operator fun invoke(x: Int, y: Int): Vector2
}

class HorizontalAxis(map: Vector2CharMap) : Axis {
    override val range = map.xRange
    override val opposite = map.yRange
    override operator fun invoke(x: Int, y: Int) = Vector2(x, y)
}

class VerticalAxis(map: Vector2CharMap) : Axis {
    override val range = map.yRange
    override val opposite = map.xRange
    override operator fun invoke(x: Int, y: Int) = Vector2(y, x)
}
