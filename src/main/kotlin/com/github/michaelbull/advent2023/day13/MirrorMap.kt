package com.github.michaelbull.advent2023.day13

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.Vector2CharMap
import com.github.michaelbull.advent2023.math.toVector2CharMap

private typealias Axis = (Int, Int) -> Vector2

fun Sequence<String>.toMirrorMap(): MirrorMap {
    val patterns = mutableListOf<Vector2CharMap>()
    val pattern = mutableListOf<String>()

    fun addPattern() {
        if (pattern.isNotEmpty()) {
            patterns += pattern.toVector2CharMap()
        }
    }

    fun reset() {
        pattern.clear()
    }

    for (line in this) {
        if (line.isEmpty()) {
            addPattern()
            reset()
        } else {
            pattern += line
        }
    }

    addPattern()

    return MirrorMap(patterns.toList())
}

data class MirrorMap(
    val patterns: List<Vector2CharMap>,
) {

    fun summarize(distinctCount: Int): Int {
        return patterns.sumOf { pattern ->
            val columnsLeft = pattern.findReflectedColumn(distinctCount) ?: 0
            val rowsAbove = pattern.findReflectedRow(distinctCount) ?: 0
            (rowsAbove * 100) + columnsLeft
        }
    }

    private fun Vector2CharMap.findReflectedColumn(distinctCount: Int): Int? {
        return xRange.drop(1).find { x ->
            countDistinctReflections(0..<x, yRange, ::vertical) == distinctCount
        }
    }

    private fun Vector2CharMap.findReflectedRow(distinctCount: Int): Int? {
        return yRange.drop(1).find { y ->
            countDistinctReflections(0..<y, xRange, ::horizontal) == distinctCount
        }
    }

    private fun Vector2CharMap.countDistinctReflections(range: IntRange, otherRange: IntRange, axis: Axis): Int {
        return range.sumOf { delta ->
            zipReflectedChars(delta, range, otherRange, axis).count(::isDistinct)
        }
    }

    private fun reflect(original: Int, delta: Int): Pair<Int, Int> {
        val a = original - delta
        val b = original + delta + 1
        return a to b
    }

    private fun Vector2CharMap.zipReflectedChars(
        delta: Int,
        range: IntRange,
        otherRange: IntRange,
        axis: Axis,
    ): List<Pair<Char, Char>> {
        val (original, reflected) = reflect(range.last, delta)
        val originalChars = otherRange.map { this[axis(original, it)] }
        val reflectedChars = otherRange.mapNotNull { this.getOrNull(axis(reflected, it)) }
        return originalChars.zip(reflectedChars)
    }

    private fun <A, B> isDistinct(pair: Pair<A, B>): Boolean {
        return pair.first != pair.second
    }

    private fun vertical(x: Int, y: Int) = Vector2(x, y)
    private fun horizontal(x: Int, y: Int) = Vector2(y, x)
}
