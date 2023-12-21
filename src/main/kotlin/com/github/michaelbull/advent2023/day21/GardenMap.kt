package com.github.michaelbull.advent2023.day21

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.Vector2.Companion.CARDINAL_DIRECTIONS
import com.github.michaelbull.advent2023.math.Vector2CharMap
import com.github.michaelbull.advent2023.math.mod
import com.github.michaelbull.advent2023.math.toVector2CharMap

fun Sequence<String>.toGardenMap(): GardenMap {
    val map = this.toVector2CharMap()
    val (start) = map.first { (_, char) -> char == 'S' }

    map[start] = '.'

    return GardenMap(map, start)
}

data class GardenMap(
    val map: Vector2CharMap,
    val start: Vector2,
) {

    fun reachablePlots(steps: Int): Int {
        return paths(steps).count()
    }

    private fun paths(steps: Int): Sequence<Path> = sequence {
        val movements = ArrayDeque<Path>()
        val visited = mutableSetOf<Path>()

        movements += Path(start, steps = 0)

        while (movements.isNotEmpty()) {
            val movement = movements.removeFirst()
            if (movement in visited) {
                continue
            }

            visited += movement

            if (movement.steps == steps) {
                yield(movement)
            } else {
                val adjacentPlots = adjacentPlots(movement.destination)
                val paths = adjacentPlots.map { Path(it, movement.steps + 1) }
                movements += paths
            }
        }
    }

    private data class Path(
        val destination: Vector2,
        val steps: Int,
    )

    private fun adjacentPlots(position: Vector2): List<Vector2> {
        return CARDINAL_DIRECTIONS
            .map(position::plus)
            .filter(::isPlotAt)
    }

    private fun isPlotAt(position: Vector2): Boolean {
        return map.getInfinitely(position) == '.'
    }

    private fun Vector2CharMap.getInfinitely(position: Vector2): Char {
        val x = position.x.mod(map.xRange)
        val y = position.y.mod(map.yRange)
        return get(Vector2(x, y))
    }
}
