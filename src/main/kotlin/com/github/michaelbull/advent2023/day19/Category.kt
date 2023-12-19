package com.github.michaelbull.advent2023.day19

sealed interface Category

data object ExtremelyCoolLooking : Category
data object Musical : Category
data object Aerodynamic : Category
data object Shiny : Category

fun Char.toCategory(): Category {
    return when (this) {
        'x' -> ExtremelyCoolLooking
        'm' -> Musical
        'a' -> Aerodynamic
        's' -> Shiny
        else -> throw IllegalArgumentException("'$this' is not a category")
    }
}
