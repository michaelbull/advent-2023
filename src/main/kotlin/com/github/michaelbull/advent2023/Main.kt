package com.github.michaelbull.advent2023

import com.github.michaelbull.advent2023.day1.Day1
import kotlin.time.measureTimedValue

fun main() {
    val puzzles = listOf(
        Day1,
    )

    for (puzzle in puzzles) {
        puzzle.solve()
    }
}

private fun <T : Any, V : Any> Puzzle<T, V>.solve() {
    println("")
    println("Day ${day}:")

    for ((index, solution) in solutions().withIndex()) {
        val (answer, duration) = measureTimedValue { solve(solution) }
        println("  part ${index + 1}:")
        println("    duration: $duration")
        println("    answer:   $answer")
    }
}
