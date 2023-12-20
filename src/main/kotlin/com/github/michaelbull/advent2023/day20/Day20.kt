package com.github.michaelbull.advent2023.day20

import com.github.michaelbull.advent2023.Puzzle

object Day20 : Puzzle<ModuleConfiguration, Long>(day = 20) {

    override fun parse(lines: Sequence<String>): ModuleConfiguration {
        return lines.toModuleConfiguration()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: ModuleConfiguration): Long {
        val pulses = sequence {
            val network = input.toNetwork()

            repeat(times = 1000) {
                yieldAll(network.pressButton())
            }
        }

        val lowPulses = pulses.count { it.strength == LowPulse }.toLong()
        val highPulses = pulses.count { it.strength == HighPulse }.toLong()

        return lowPulses * highPulses
    }

    fun part2(input: ModuleConfiguration): Long {
        return input.toNetwork().minButtonPresses("rx", LowPulse)
    }
}
