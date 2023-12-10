package com.github.michaelbull.advent2023.day3

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.toVector2CharMap

fun Sequence<String>.toEngineSchematic(): EngineSchematic {
    val data = this.toVector2CharMap()

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

    val symbols = data
        .filter { (_, value) -> value.isEngineSymbol() }
        .map { (position, value) -> EngineSymbol(position, value) }

    return EngineSchematic(
        numbers,
        symbols
    )
}

data class EngineSchematic(
    val numbers: List<EngineNumber>,
    val symbols: List<EngineSymbol>,
) {

    private val symbolPositions = symbols.map(EngineSymbol::position)
    private val gearPositions = symbols.filter(EngineSymbol::isGear).map(EngineSymbol::position)

    fun partNumbers(): List<Int> {
        return numbers
            .filter(::isAdjacentToSymbol)
            .map(EngineNumber::value)
    }

    fun gearRatios(): List<Int> {
        return gearPositions
            .map(::numbersAdjacentTo)
            .filter(::isBinary)
            .map(::ratio)
    }

    private fun isAdjacentToSymbol(number: EngineNumber): Boolean {
        return symbolPositions.any(number::adjacentTo)
    }

    private fun numbersAdjacentTo(position: Vector2): List<EngineNumber> {
        return numbers.filter { number ->
            number adjacentTo position
        }
    }

    private fun <E> isBinary(collection: Collection<E>): Boolean {
        return collection.size == 2
    }

    private fun ratio(list: List<EngineNumber>): Int {
        return list[0].value * list[1].value
    }
}

