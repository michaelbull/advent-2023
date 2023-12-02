package com.github.michaelbull.advent2023

import java.io.InputStream

typealias Solution<T, V> = (input: T) -> V

abstract class Puzzle<T : Any, V : Any>(val day: Int) {

    abstract fun parse(lines: Sequence<String>): T
    abstract fun solutions(): Iterable<Solution<T, V>>

    fun parse(input: String): T {
        val lines = input.trimIndent().lineSequence()
        return parse(lines)
    }

    fun solve(solution: Solution<T, V>, lines: Sequence<String>): V {
        return solution(parse(lines))
    }

    fun solve(solution: Solution<T, V>, input: String): V {
        return solution(parse(input))
    }

    fun solve(solution: Solution<T, V>): V {
        return inputStream()
            .bufferedReader()
            .useLines { lines -> solve(solution, lines) }
    }

    private fun inputStream(): InputStream {
        return Puzzle::class.java.getResourceAsStream("day$day.txt") ?: error("No input file found for day $day")
    }
}
