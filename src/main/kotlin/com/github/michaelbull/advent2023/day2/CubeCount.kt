package com.github.michaelbull.advent2023.day2

data class CubeCount(
    val count: Int,
    val color: CubeColor,
) {
    override fun toString(): String {
        return "$count $color"
    }

    companion object {
        const val SEPARATOR = ", "
    }
}

private val REGEX = "(\\d+) (red|green|blue)".toRegex()

fun String.toCubeCount(): CubeCount {
    val result = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match $REGEX"
    }

    val (count, color) = result.destructured

    return CubeCount(
        count = count.toInt(),
        color = color.toCubeColor()
    )
}
