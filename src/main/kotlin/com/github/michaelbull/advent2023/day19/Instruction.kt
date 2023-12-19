package com.github.michaelbull.advent2023.day19

sealed interface Instruction

data object Accept : Instruction
data object Reject : Instruction

data class Send(val workflow: String) : Instruction
