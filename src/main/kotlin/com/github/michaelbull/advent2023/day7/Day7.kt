package com.github.michaelbull.advent2023.day7

import com.github.michaelbull.advent2023.Puzzle

object Day7 : Puzzle<Sequence<String>, Long>(day = 7) {

    override fun parse(lines: Sequence<String>): Sequence<String> {
        return lines
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: Sequence<String>): Long {
        return input
            .map(String::toBid)
            .sortedWith(RankComparator)
            .withIndex()
            .sumOf(::winnings)
    }

    fun part2(input: Sequence<String>): Long {
        return input
            .map(String::toJokerBid)
            .sortedWith(RankComparator)
            .withIndex()
            .sumOf(::winnings)
    }

    private fun winnings(value: IndexedValue<Bid>): Long {
        val (index, bid) = value
        val rank = index + 1
        return bid.amount * rank
    }
}
