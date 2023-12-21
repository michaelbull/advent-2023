package com.github.michaelbull.advent2023.day2

import com.github.michaelbull.advent2023.Puzzle

object Day2 : Puzzle<Sequence<Game>, Int>(day = 2) {

    override fun parse(lines: Sequence<String>): Sequence<Game> {
        return lines.map(String::toGame)
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Sequence<Game>): Int {
        val configuration = mapOf(
            CubeColor.Red to 12,
            CubeColor.Green to 13,
            CubeColor.Blue to 14,
        )

        return input
            .filter { it.isValid(configuration) }
            .sumOf(Game::id)
    }

    fun part2(input: Sequence<Game>): Int {
        return input.sumOf(Game::minimumPower)
    }
}
