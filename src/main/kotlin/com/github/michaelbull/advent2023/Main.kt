package com.github.michaelbull.advent2023

import com.github.michaelbull.advent2023.day1.Day1
import com.github.michaelbull.advent2023.day2.Day2
import com.github.michaelbull.advent2023.day3.Day3
import com.github.michaelbull.advent2023.day4.Day4
import com.github.michaelbull.advent2023.day5.Day5
import com.github.michaelbull.advent2023.day6.Day6
import com.github.michaelbull.advent2023.day7.Day7
import com.github.michaelbull.advent2023.day8.Day8
import com.github.michaelbull.advent2023.day9.Day9
import kotlin.time.measureTimedValue

fun main() {
    val puzzles = listOf(
        Day1,
        Day2,
        Day3,
        Day4,
        Day5,
        Day6,
        Day7,
        Day8,
        Day9,
    )

    for (puzzle in puzzles) {
        puzzle.solve()
    }
}

private fun <T : Any, V : Any> Puzzle<T, V>.solve() {
    println("")
    println("Day $day:")

    for ((index, solution) in solutions().withIndex()) {
        val (answer, duration) = measureTimedValue { solve(solution) }
        println("  part ${index + 1}:")
        println("    duration: $duration")
        println("    answer:   $answer")
    }
}
