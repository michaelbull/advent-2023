package com.github.michaelbull.advent2023.day11

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.manhattanDistance

fun Sequence<String>.toImage(): Image {
    val galaxies = buildSet {
        for ((y, line) in this@toImage.withIndex()) {
            for ((x, char) in line.withIndex()) {
                if (char == '#') {
                    add(Vector2(x, y))
                } else if (char != '.') {
                    throw IllegalArgumentException("$this is not a pixel")
                }
            }
        }
    }

    return Image(galaxies)
}

data class Image(
    val galaxies: Set<Vector2>,
) {

    private val galaxiesByColumn = galaxies.sortedBy(Vector2::x)
    private val galaxiesByRow = galaxies.sortedBy(Vector2::y)

    fun sumShortestPathLengths(): Long {
        return galaxies
            .cartesianProduct()
            .distinctBySet()
            .sumOf { (from, to) -> manhattanDistance(from, to).toLong() }
    }

    fun expand(horizontalFactor: Int, verticalFactor: Int = horizontalFactor): Image {
        return expandHorizontally(horizontalFactor).expandVertically(verticalFactor)
    }

    private fun expandHorizontally(factor: Int): Image {
        return copy(galaxies = galaxiesByColumn.expand(factor, Vector2::x, ::horizontal))
    }

    private fun expandVertically(factor: Int): Image {
        return copy(galaxies = galaxiesByRow.expand(factor, Vector2::y, ::vertical))
    }

    private fun List<Vector2>.expand(factor: Int, dimension: Dimension, axis: Axis): Set<Vector2> {
        val first = first()
        val pairs = zipWithNext()

        return buildSet {
            add(first)

            var expansion = Vector2.ZERO
            val multiplicand = factor - 1

            for ((a, b) in pairs) {
                val delta = dimension(b) - dimension(a)
                val emptyCount = delta - 1

                if (emptyCount > 0) {
                    expansion += axis(emptyCount * multiplicand)
                }

                add(b + expansion)
            }
        }
    }

    private fun horizontal(x: Int) = Vector2(x, 0)
    private fun vertical(y: Int) = Vector2(0, y)
}

private typealias Dimension = (Vector2) -> Int
private typealias Axis = (Int) -> Vector2

private fun <T> Iterable<T>.cartesianProduct(): List<Pair<T, T>> {
    return flatMap { left ->
        filter { right -> left != right }.map { right ->
            left to right
        }
    }
}

private fun <A, B> List<Pair<A, B>>.distinctBySet(): List<Pair<A, B>> {
    return distinctBy(Pair<A, B>::toSet)
}

private fun <T> Pair<T, T>.toSet(): Set<T> {
    return setOf(first, second)
}