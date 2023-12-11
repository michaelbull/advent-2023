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
        return expand(factor, galaxiesByColumn, Vector2::x) { x ->
            Vector2(x, 0)
        }
    }

    private fun expandVertically(factor: Int): Image {
        return expand(factor, galaxiesByRow, Vector2::y) { y ->
            Vector2(0, y)
        }
    }

    private fun expand(
        factor: Int,
        orderedGalaxies: List<Vector2>,
        dimension: (Vector2) -> Int,
        expand: (Int) -> Vector2,
    ): Image {
        val expandedGalaxies = buildSet {
            add(orderedGalaxies.first())

            var expansion = Vector2.ZERO
            val multiplicand = factor - 1

            for ((a, b) in orderedGalaxies.zipWithNext()) {
                val delta = dimension(b) - dimension(a)
                val emptyCount = delta - 1

                if (emptyCount > 0) {
                    expansion += expand(emptyCount * multiplicand)
                }

                add(b + expansion)
            }
        }

        return copy(galaxies = expandedGalaxies.toSet())
    }
}

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
