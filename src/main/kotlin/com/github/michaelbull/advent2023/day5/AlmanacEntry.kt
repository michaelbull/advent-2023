package com.github.michaelbull.advent2023.day5

fun String.toAlmanacEntry(): AlmanacEntry {
    val (destinationStart, sourceStart, length) = split(" ").map(String::toLong)

    val sourceEnd = sourceStart + length
    val destinationEnd = destinationStart + length

    return AlmanacEntry(
        source = sourceStart..<sourceEnd,
        destination = destinationStart..<destinationEnd
    )
}

data class AlmanacEntry(
    val source: LongRange,
    val destination: LongRange,
) {

    fun getOrNull(value: Long): Long? {
        return if (value in source) {
            val offset = value - source.first
            destination.first + offset
        } else {
            null
        }
    }

    operator fun get(value: Long): Long {
        return getOrNull(value) ?: value
    }
}
