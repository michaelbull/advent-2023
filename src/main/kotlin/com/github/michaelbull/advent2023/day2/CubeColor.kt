package com.github.michaelbull.advent2023.day2

sealed interface CubeColor {
    data object Red : CubeColor {
        override fun toString(): String {
            return "red"
        }
    }

    data object Green : CubeColor {
        override fun toString(): String {
            return "green"
        }
    }

    data object Blue : CubeColor {
        override fun toString(): String {
            return "blue"
        }
    }
}

val CUBE_COLORS = setOf(
    CubeColor.Red,
    CubeColor.Green,
    CubeColor.Blue
)

fun String.toCubeColor(): CubeColor {
    return when (this) {
        "red" -> CubeColor.Red
        "green" -> CubeColor.Green
        "blue" -> CubeColor.Blue
        else -> throw IllegalArgumentException("$this is not a cube color")
    }
}
