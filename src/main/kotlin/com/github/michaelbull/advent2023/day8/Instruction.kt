package com.github.michaelbull.advent2023.day8

sealed interface Instruction

data object Left : Instruction
data object Right : Instruction

fun Char.toInstruction(): Instruction {
    return when (this) {
        'L' -> Left
        'R' -> Right
        else -> throw IllegalArgumentException("$this is not an instruction")
    }
}
