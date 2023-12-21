package com.github.michaelbull.advent2023.day21

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.Vector2.Companion.CARDINAL_DIRECTIONS
import com.github.michaelbull.advent2023.math.Vector2CharMap
import com.github.michaelbull.advent2023.math.Vector3
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
        return paths(steps).count { path ->
            (path.steps and 1) == (steps and 1)
        }
    }

    fun infiniteReachablePlots(steps: Int): Long {
        val x0 = steps % map.width
        val x1 = x0 + map.width
        val x2 = x1 + map.width

        val paths = paths(x2)

        val y0 = paths.count { (_, steps) -> (steps and 1) == (x0 and 1) && steps <= x0 }
        val y1 = paths.count { (_, steps) -> (steps and 1) == (x1 and 1) && steps <= x1 }
        val y2 = paths.count { (_, steps) -> (steps and 1) == (x2 and 1) && steps <= x2 }

        val polynomial = Vector3(y0, y1, y2).lagrange()

        val x = (steps - start.x) / map.width

        return x.toLong().quadratic(polynomial)
    }

    private fun paths(steps: Int): Sequence<Path> = sequence {
        val queue = ArrayDeque<Path>()
        val visited = mutableSetOf<Vector2>()

        queue += Path(start, 0)
        visited += start

        while (queue.isNotEmpty()) {
            val path = queue.removeFirst()

            yield(path)

            val nextStep = path.steps + 1
            if (nextStep <= steps) {
                val adjacentPlots = adjacentPlots(path.destination)
                val unvisitedPlots = adjacentPlots.filter { it !in visited }
                val paths = unvisitedPlots.map { destination -> Path(destination, nextStep) }

                queue += paths
                visited += unvisitedPlots
            }
        }
    }

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

    private data class Path(
        val destination: Vector2,
        val steps: Int,
    )

    /**
     * Lagrange's Interpolation formula for ax^2 + bx + c with x=[0,1,2] and y=[y0,y1,y2] we have
     *   f(x) = (x^2-3x+2) * y0/2 - (x^2-2x)*y1 + (x^2-x) * y2/2
     * so the coefficients are:
     * a = y0/2 - y1 + y2/2
     * b = -3*y0/2 + 2*y1 - y2/2
     * c = y0
     *
     * [Reddit](https://www.reddit.com/r/adventofcode/comments/18nevo3/2023_day_21_solutions/keb8ud3/)
     */
    private fun Vector3.lagrange(): Vector3 {
        return Vector3(
            x = (x / 2.0 - y + z / 2.0).toInt(),
            y = (-3 * (x / 2.0) + 2 * y - z / 2.0).toInt(),
            z = x,
        )
    }

    private fun Long.quadratic(polynomial: Vector3): Long {
        val (a, b, c) = polynomial
        return (a * this * this) + (b * this) + c
    }
}
