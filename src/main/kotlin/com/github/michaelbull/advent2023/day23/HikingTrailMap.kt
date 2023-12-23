package com.github.michaelbull.advent2023.day23

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.Vector2.Companion.CARDINAL_DIRECTIONS
import com.github.michaelbull.advent2023.math.Vector2.Companion.EAST
import com.github.michaelbull.advent2023.math.Vector2.Companion.NORTH
import com.github.michaelbull.advent2023.math.Vector2.Companion.SOUTH
import com.github.michaelbull.advent2023.math.Vector2.Companion.WEST
import com.github.michaelbull.advent2023.math.Vector2CharMap
import com.github.michaelbull.advent2023.math.toVector2CharMap

fun Sequence<String>.toHikingTrailMap(): HikingTrailMap {
    return HikingTrailMap(this.toVector2CharMap())
}

data class HikingTrailMap(
    val trails: Vector2CharMap,
) {

    private val start = Vector2(1, 0)
    private val end = Vector2(trails.xRange.last - 1, trails.yRange.last)

    private val junctionPositions = trails
        .filter(::isJunction)
        .map(Pair<Vector2, Char>::first)

    fun longestHikeSteps(): Int {
        return longestHikeSteps(
            tile = start,
            findPaths = ::slopedPaths,
        )
    }

    fun levelledOutLongestHikeSteps(): Int {
        return longestHikeSteps(
            tile = start,
            findPaths = levelledOutPaths(),
        )
    }

    private fun longestHikeSteps(
        tile: Vector2,
        visited: MutableSet<Vector2> = mutableSetOf(),
        cumulativeSteps: Int = 0,
        findPaths: (Vector2) -> Iterable<Path>,
    ): Int {
        return if (tile == end) {
            cumulativeSteps
        } else {
            visited += tile

            val paths = findPaths(tile)
            val unvisitedPaths = paths.filter { it.tile !in visited }

            val max = unvisitedPaths.maxOfOrNull { (destination, steps) ->
                longestHikeSteps(destination, visited, cumulativeSteps + steps, findPaths)
            }

            visited -= tile

            max ?: 0
        }
    }

    private fun slopedPaths(position: Vector2): Iterable<Path> {
        val trail = trails[position]
        val delta = SLOPES_TO_DELTA[trail]

        return if (delta == null) {
            position.adjacentTiles().map { Path(it, 1) }
        } else {
            val downhill = position + delta

            if (downhill in trails) {
                listOf(Path(downhill, 1))
            } else {
                emptyList()
            }
        }
    }

    private fun levelledOutPaths(): (Vector2) -> Iterable<Path> {
        val pathsByJunctionPosition = junctionPositions.associateWith {
            mutableListOf<Path>()
        }

        for ((junctionPosition, junctionPaths) in pathsByJunctionPosition) {
            val current = mutableSetOf(junctionPosition)
            val visited = mutableSetOf(junctionPosition)
            var steps = 1

            while (current.isNotEmpty()) {
                val next = mutableSetOf<Vector2>()

                for (position in current) {
                    val adjacentTiles = position.adjacentTiles()
                    val unvisitedTiles = adjacentTiles.filter { it !in visited }

                    for (unvisitedTile in unvisitedTiles) {
                        if (unvisitedTile in pathsByJunctionPosition) {
                            junctionPaths += Path(unvisitedTile, steps)
                        } else {
                            visited += unvisitedTile
                            next += unvisitedTile
                        }
                    }
                }

                current.clear()
                current += next
                steps++
            }
        }

        return pathsByJunctionPosition::getValue
    }

    private fun isJunction(pair: Pair<Vector2, Char>): Boolean {
        val (position, tile) = pair

        return when (position) {
            start -> true
            end -> true
            else -> tile.isPath() && position.isJunction()
        }
    }

    private fun Vector2.isJunction(): Boolean {
        return adjacentTiles().size > 2
    }

    private fun Vector2.adjacentTiles(): List<Vector2> {
        return CARDINAL_DIRECTIONS
            .map(::plus)
            .filter { it in trails && !trails[it].isForest() }
    }

    private fun Char.isPath(): Boolean {
        return this == PATH
    }

    private fun Char.isForest(): Boolean {
        return this == FOREST
    }

    private data class Path(
        val tile: Vector2,
        val steps: Int,
    )

    private companion object {
        private const val PATH = '.'
        private const val FOREST = '#'

        private const val SLOPE_UP = '^'
        private const val SLOPE_RIGHT = '>'
        private const val SLOPE_DOWN = 'v'
        private const val SLOPE_LEFT = '<'

        private val SLOPES_TO_DELTA = mapOf(
            SLOPE_UP to SOUTH,
            SLOPE_RIGHT to EAST,
            SLOPE_DOWN to NORTH,
            SLOPE_LEFT to WEST,
        )
    }
}
