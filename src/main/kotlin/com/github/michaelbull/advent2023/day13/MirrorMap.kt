package com.github.michaelbull.advent2023.day13

import com.github.michaelbull.advent2023.math.Vector2CharMap
import com.github.michaelbull.advent2023.math.toVector2CharMap

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
            pattern.summarize(distinctCount)
        }
    }

    private fun Vector2CharMap.summarize(distinctCount: Int): Int {
        val columnsLeft = findReflectedX(distinctCount) ?: 0
        val rowsAbove = findReflectedY(distinctCount) ?: 0
        return (rowsAbove * 100) + columnsLeft
    }

    private fun Vector2CharMap.findReflectedX(distinctCount: Int): Int? {
        return findReflected(distinctCount, HorizontalAxis(this))
    }

    private fun Vector2CharMap.findReflectedY(distinctCount: Int): Int? {
        return findReflected(distinctCount, VerticalAxis(this))
    }

    private fun Vector2CharMap.findReflected(distinctCount: Int, axis: Axis): Int? {
        return axis.range.drop(1).find { i ->
            countDistinctReflections(axis, 0..<i) == distinctCount
        }
    }

    private fun Vector2CharMap.countDistinctReflections(axis: Axis, range: IntRange): Int {
        return range.sumOf { delta ->
            zipReflectedChars(axis, range.last, delta).count(::isDistinct)
        }
    }

    private fun Vector2CharMap.zipReflectedChars(axis: Axis, original: Int, delta: Int): List<Pair<Char, Char>> {
        val originalChars = axis.opposite.map { this[axis(original - delta, it)] }
        val reflectedChars = axis.opposite.mapNotNull { this.getOrNull(axis(original + delta + 1, it)) }
        return originalChars.zip(reflectedChars)
    }

    private fun <A, B> isDistinct(pair: Pair<A, B>): Boolean {
        return pair.first != pair.second
    }
}
