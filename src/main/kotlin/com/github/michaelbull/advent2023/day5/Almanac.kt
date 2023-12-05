package com.github.michaelbull.advent2023.day5

private val SEEDS_REGEX = "seeds: (.*)".toRegex()
private val MAP_REGEX = "(.*)-to-(.*) map:".toRegex()

fun Sequence<String>.toAlmanac(): Almanac {
    var seeds = emptyList<Long>()
    var source = ""
    var destination = ""
    val entries = mutableListOf<AlmanacEntry>()
    val maps = mutableListOf<AlmanacMap>()

    fun addMap() {
        if (entries.isNotEmpty()) {
            check(source.isNotEmpty())
            check(destination.isNotEmpty())

            maps += AlmanacMap(
                source = source,
                destination = destination,
                entries = entries.toList()
            )
        }
    }

    fun reset() {
        source = ""
        destination = ""
        entries.clear()
    }

    for ((index, line) in withIndex()) {
        if (index == 0) {
            seeds = line.toSeedList()
        } else if (line.isEmpty()) {
            addMap()
            reset()
        } else {
            val result = MAP_REGEX.matchEntire(line)

            if (result != null) {
                val values = result.groupValues
                source = values[1]
                destination = values[2]
            } else {
                entries += line.toAlmanacEntry()
            }
        }
    }

    addMap()

    return Almanac(
        seeds = seeds.toList(),
        maps = maps.toList()
    )
}

data class Almanac(
    val seeds: List<Long>,
    val maps: List<AlmanacMap>
)

private fun String.toSeedList(): List<Long> {
    val result = requireNotNull(SEEDS_REGEX.matchEntire(this)) {
        "$this must match $SEEDS_REGEX"
    }

    val (_, numbers) = result.groupValues

    return numbers
        .split(" ")
        .map(String::toLong)
}
