package com.github.michaelbull.advent2023.day18

private val DIRECTION_REGEX = "[UDLR]".toRegex()
private val LENGTH_REGEX = "\\d+".toRegex()
private val COLOR_CODE_REGEX = "[a-fA-F0-9]{6}".toRegex()
private val REGEX = "($DIRECTION_REGEX) ($LENGTH_REGEX) \\(#($COLOR_CODE_REGEX)\\)".toRegex()

fun String.toInstruction(): Instruction {
    val result = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match $REGEX"
    }

    val (direction, length, color) = result.destructured

    return Instruction(
        direction = direction.single().toDirection(),
        length = length.toInt(),
        color = color,
    )
}

data class Instruction(
    val direction: Direction,
    val length: Int,
    val color: String,
)
