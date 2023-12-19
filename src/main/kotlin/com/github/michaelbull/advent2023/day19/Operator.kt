package com.github.michaelbull.advent2023.day19

sealed interface Operator

data object MoreThan : Operator
data object LessThan : Operator

fun Char.toOperator(): Operator {
    return when (this) {
        '>' -> MoreThan
        '<' -> LessThan
        else -> throw IllegalArgumentException("'$this' is not an operator")
    }
}
