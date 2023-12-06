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

    fun getOrNull(key: Long): Long? {
        return if (key in source) {
            val offset = key - source.first
            destination.first + offset
        } else {
            null
        }
    }

    operator fun get(key: Long): Long {
        return getOrNull(key) ?: key
    }
}
