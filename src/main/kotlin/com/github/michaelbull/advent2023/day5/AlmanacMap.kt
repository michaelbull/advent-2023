package com.github.michaelbull.advent2023.day5

fun Iterable<AlmanacMap>.lookup(index: Long): Long {
    return fold(index) { acc, map ->
        map[acc]
    }
}

fun Iterable<AlmanacMap>.lookup(ranges: List<LongRange>): List<LongRange> {
    return fold(ranges) { acc, map ->
        acc.flatMap(map::get)
    }
}

data class AlmanacMap(
    val source: String,
    val destination: String,
    val entries: List<AlmanacEntry>,
) {

    private val sortedEntries = entries.sortedBy { it.source.first }

    operator fun get(key: Long): Long {
        return entries
            .firstNotNullOfOrNull { entry -> entry.getOrNull(key) }
            ?: key
    }

    operator fun get(keys: LongRange): Sequence<LongRange> = sequence {
        var start = keys.first
        val end = keys.last

        for (entry in sortedEntries) {
            val (source, destination) = entry

            val startWithin = start in source
            val endWithin = end in source

            when {
                startWithin && endWithin -> {
                    yield(entry[start]..entry[end])
                    return@sequence
                }

                startWithin -> {
                    yield(entry[start]..destination.last)
                    start = source.last + 1
                }

                endWithin -> {
                    yield(start..<source.first)
                    yield(destination.first..entry[end])
                    return@sequence
                }
            }
        }

        yield(start..end)
    }
}
