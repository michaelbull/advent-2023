package com.github.michaelbull.advent2023.day20

sealed interface Module {
    val name: String
    val destinations: List<String>
}

sealed interface FlipFlop : Module {

    data class On(
        override val name: String,
        override val destinations: List<String>,
    ) : FlipFlop

    data class Off(
        override val name: String,
        override val destinations: List<String>,
    ) : FlipFlop
}

data class Conjunction(
    override val name: String,
    override val destinations: List<String>,
    val history: Map<String, PulseStrength> = emptyMap(),
) : Module

data class Broadcast(
    override val name: String,
    override val destinations: List<String>,
) : Module

fun String.toModule(): Module {
    val (moduleName, destinationsCsv) = split(" -> ")
    val destinations = destinationsCsv.split(", ")

    return when (moduleName.first()) {
        '%' -> FlipFlop.Off(moduleName.drop(1), destinations)
        '&' -> Conjunction(moduleName.drop(1), destinations)
        else -> Broadcast(moduleName, destinations)
    }
}
