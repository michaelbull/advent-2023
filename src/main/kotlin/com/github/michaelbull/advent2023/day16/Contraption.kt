package com.github.michaelbull.advent2023.day16

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.Vector2CharMap
import com.github.michaelbull.advent2023.math.toVector2CharMap

fun Sequence<String>.toContraption(): Contraption {
    return Contraption(this.toVector2CharMap())
}

data class Contraption(
    val layout: Vector2CharMap,
) {

    fun energisedTileCount(beam: Beam): Int {
        return beam.travel()
            .map(Beam::position)
            .distinct()
            .size
    }

    fun maxEnergisedTileCount(): Int {
        return edgeTileBeams().maxOf(::energisedTileCount)
    }

    private fun Beam.travel(visited: MutableSet<Beam> = mutableSetOf()): Set<Beam> {
        val next = travelForwards()

        return if (next in visited) {
            visited
        } else if (next.position !in layout) {
            visited
        } else {
            visited += next

            fun forward() = next.travel(visited)
            fun up() = next(Up).travel(visited)
            fun right() = next(Right).travel(visited)
            fun down() = next(Down).travel(visited)
            fun left() = next(Left).travel(visited)

            when (val tile = layout[next.position]) {
                EMPTY_SPACE -> forward()

                FORWARD_MIRROR -> when (direction) {
                    Up -> right()
                    Right -> up()
                    Down -> left()
                    Left -> down()
                }

                BACKWARD_MIRROR -> when (direction) {
                    Up -> left()
                    Right -> down()
                    Down -> right()
                    Left -> up()
                }

                VERTICAL_SPLITTER -> when (direction) {
                    Up -> forward()
                    Right -> up().also { down() }
                    Down -> forward()
                    Left -> up().also { down() }
                }

                HORIZONTAL_SPLITTER -> when (direction) {
                    Up -> left().also { right() }
                    Right -> forward()
                    Down -> left().also { right() }
                    Left -> forward()
                }

                else -> error("unknown tile '$tile'")
            }
        }
    }

    private fun edgeTileBeams(): List<Beam> {
        return upwardBeams() +
            rightwardBeams() +
            downwardBeams() +
            leftwardBeams()
    }

    private fun topRow(): List<Vector2> {
        return layout.xRange.map { x ->
            Vector2(x, layout.yRange.first - 1)
        }
    }

    private fun rightColumn(): List<Vector2> {
        return layout.yRange.map { y ->
            Vector2(layout.xRange.last + 1, y)
        }
    }

    private fun bottomRow(): List<Vector2> {
        return layout.xRange.map { x ->
            Vector2(x, layout.yRange.last + 1)
        }
    }

    private fun leftColumn(): List<Vector2> {
        return layout.yRange.map { y ->
            Vector2(layout.xRange.first - 1, y)
        }
    }

    private fun upwardBeams(): List<Beam> {
        return bottomRow().map { Beam(it, Up) }
    }

    private fun rightwardBeams(): List<Beam> {
        return leftColumn().map { Beam(it, Right) }
    }

    private fun downwardBeams(): List<Beam> {
        return topRow().map { Beam(it, Down) }
    }

    private fun leftwardBeams(): List<Beam> {
        return rightColumn().map { Beam(it, Left) }
    }

    private companion object {
        private const val EMPTY_SPACE = '.'
        private const val FORWARD_MIRROR = '/'
        private const val BACKWARD_MIRROR = '\\'
        private const val HORIZONTAL_SPLITTER = '-'
        private const val VERTICAL_SPLITTER = '|'
    }
}
