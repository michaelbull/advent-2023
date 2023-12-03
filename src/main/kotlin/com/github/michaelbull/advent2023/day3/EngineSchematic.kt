package com.github.michaelbull.advent2023.day3

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.Vector2CharMap

fun Sequence<String>.toEngineSchematic(): EngineSchematic {
    val lines = this.toList()
    val width = lines.first().length
    val height = lines.size

    val data = Vector2CharMap(width, height) { (x, y) ->
        val line = lines[y]
        val char = line[x]
        char
    }

    val numbers = buildList {
        for (y in data.yRange) {
            var startPosition: Vector2? = null
            var number = 0

            for (x in data.xRange) {
                val position = Vector2(x, y)
                val char = data[position]

                if (char.isDigit()) {
                    if (startPosition == null) {
                        startPosition = position
                    }

                    number *= 10
                    number += char.digitToInt()
                } else if (startPosition != null) {
                    add(number.inEngineAt(startPosition))
                    startPosition = null
                    number = 0
                }
            }

            if (startPosition != null) {
                add(number.inEngineAt(startPosition))
            }
        }
    }

    val symbols = data.mapNotNull { position ->
        val char = data[position]

        if (char.isEngineSymbol()) {
            char.inEngineAt(position)
        } else {
            null
        }
    }

    return EngineSchematic(
        numbers,
        symbols
    )
}

data class EngineSchematic(
    val numbers: List<EngineNumber>,
    val symbols: List<EngineSymbol>,
) {

    fun partNumbers(): List<Int> {
        return numbers
            .filter(::adjacentToSymbol)
            .map(EngineNumber::value)
    }

    fun gearRatios(): List<Int> {
        return numbersAdjacentToGears()
            .map { (a, b) -> a * b }
    }

    private fun adjacentToSymbol(number: EngineNumber): Boolean {
        return symbols.any { (symbolPosition) ->
            number adjacentTo symbolPosition
        }
    }

    private fun gearSymbols(): List<EngineSymbol> {
        return symbols.filter(EngineSymbol::isGear)
    }

    private fun numbersAdjacentToGears(): List<Pair<Int, Int>> {
        return gearSymbols()
            .map { (gearPosition) -> numbersAdjacentTo(gearPosition) }
            .filter { adjacentNumbers -> adjacentNumbers.size == 2 }
            .map { (first, second) -> first.value to second.value }
    }

    private fun numbersAdjacentTo(position: Vector2): List<EngineNumber> {
        return numbers.filter { number ->
            number adjacentTo position
        }
    }
}

