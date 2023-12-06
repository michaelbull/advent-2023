package com.github.michaelbull.advent2023.day3

import com.github.michaelbull.advent2023.math.Vector2

const val GEAR_SYMBOL = '*'

val ENGINE_SYMBOLS = setOf(
    GEAR_SYMBOL,
    '#',
    '&',
    '@',
    '$',
    '+',
    '-',
    '%',
    '/',
    '='
)

fun Char.isEngineSymbol(): Boolean {
    return this in ENGINE_SYMBOLS
}

fun Char.isGearSymbol(): Boolean {
    return this == GEAR_SYMBOL
}

data class EngineSymbol(
    override val position: Vector2,
    val value: Char,
) : EngineItem {
    fun isGear(): Boolean {
        return value.isGearSymbol()
    }
}
