package com.github.michaelbull.advent2023.day5

fun String.toAlmanacEntry(): AlmanacEntry {
    val (destinationRangeStart, sourceRangeStart, rangeLength) = split(" ").map(String::toLong)

    val sourceRangeEnd = sourceRangeStart + rangeLength
    val destinationRangeEnd = destinationRangeStart + rangeLength

    return AlmanacEntry(
        source = sourceRangeStart until sourceRangeEnd,
        destination = destinationRangeStart until destinationRangeEnd
    )
}

data class AlmanacEntry(
    val source: LongRange,
    val destination: LongRange,
) {

    fun reverse(): AlmanacEntry {
        return copy(
            source = destination,
            destination = source
        )
    }

    operator fun get(value: Long): Long? {
        return if (value in source) {
            val offset = value - source.first
            destination.first + offset
        } else {
            null
        }
    }
}
