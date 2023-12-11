package com.github.michaelbull.advent2023.day10

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.Vector2CharMap
import com.github.michaelbull.advent2023.math.toVector2CharMap

fun Sequence<String>.toPipeMap(): PipeMap {
    val tiles = this.toVector2CharMap()
    val (startTile) = tiles.first { (_, char) -> char == 'S' }

    tiles[startTile] = tiles.directionsConnectedTo(startTile).pipe()

    return PipeMap(tiles, startTile)
}

data class PipeMap(
    val tiles: Vector2CharMap,
    val startTile: Vector2,
) {

    fun farthestStepCount(): Int {
        return path().count() / 2
    }

    fun enclosedTileCount(): Int {
        val path = path().toSet()
        var count = 0

        for (y in tiles.yRange) {
            var enclosed = false

            for (x in tiles.xRange) {
                val tile = Vector2(x, y)

                if (tile in path) {
                    val pipe = tiles[tile]
                    val north = pipe.directions().any { it == NORTH }

                    if (north) {
                        enclosed = !enclosed
                    }
                } else if (enclosed) {
                    count++
                }
            }
        }

        return count
    }

    private fun path() = sequence {
        val visited = mutableSetOf<Vector2>()
        var tile = startTile

        while (true) {
            visited += tile

            val adjacentTiles = tile.adjacentTiles()
            val unvisitedTile = adjacentTiles.firstOrNull { it !in visited }

            if (unvisitedTile != null) {
                yield(unvisitedTile)
                tile = unvisitedTile
            } else if (startTile in adjacentTiles) {
                yield(startTile)
                break
            }
        }
    }

    private fun Vector2.adjacentTiles(): List<Vector2> {
        return tiles[this].directions().map(::plus)
    }
}

private val NORTH = Vector2(0, -1)
private val EAST = Vector2(1, 0)
private val SOUTH = Vector2(0, 1)
private val WEST = Vector2(-1, 0)

private val CARDINAL_DIRECTIONS = setOf(
    NORTH,
    EAST,
    SOUTH,
    WEST
)

private val DIRECTIONS_BY_PIPE = mapOf(
    '|' to setOf(NORTH, SOUTH),
    '-' to setOf(EAST, WEST),
    'L' to setOf(NORTH, EAST),
    'J' to setOf(NORTH, WEST),
    '7' to setOf(SOUTH, WEST),
    'F' to setOf(SOUTH, EAST),
    '.' to emptySet()
)

private val PIPE_BY_DIRECTIONS = DIRECTIONS_BY_PIPE
    .map { (key, value) -> value to key }
    .toMap()

private fun Char.directions(): Set<Vector2> {
    return DIRECTIONS_BY_PIPE.getValue(this)
}

private fun Set<Vector2>.pipe(): Char {
    return PIPE_BY_DIRECTIONS.getValue(this)
}

private fun Vector2CharMap.isConnected(tile: Vector2, direction: Vector2): Boolean {
    val adjacentTile = tile + direction
    val adjacentPipe = getOrNull(adjacentTile) ?: return false
    val adjacentDirections = adjacentPipe.directions()
    val oppositeDirection = -direction
    return oppositeDirection in adjacentDirections
}

private fun Vector2CharMap.directionsConnectedTo(tile: Vector2): Set<Vector2> {
    return CARDINAL_DIRECTIONS
        .filter { direction -> isConnected(tile, direction) }
        .toSet()
}
