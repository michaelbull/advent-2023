package com.github.michaelbull.advent2023.day5

fun Iterable<AlmanacMap>.lookup(index: Long): Long {
    return fold(index) { acc, map ->
        map[acc]
    }
}

fun Iterable<AlmanacMap>.lookup(ranges: List<LongRange>): List<LongRange> {
    return fold(ranges) { acc, map ->
        map[acc]
    }
}

data class AlmanacMap(
    val source: String,
    val destination: String,
    val entries: List<AlmanacEntry>
) {

    private val sortedEntries = entries.sortedBy { it.source.first }

    operator fun get(key: Long): Long {
        return entries
            .firstNotNullOfOrNull { entry -> entry.getOrNull(key) }
            ?: key
    }

    operator fun get(ranges: List<LongRange>): List<LongRange> {
        return ranges.flatMap(::get)
    }

    private fun get(range: LongRange): List<LongRange> = buildList {
        var start = range.first
        val end = range.last

        for (entry in sortedEntries) {
            val (source, destination) = entry

            if (start in source) {
                if (end in source) {
                    add(entry[start]..entry[end])
                    start = end + 1
                    break
                } else {
                    add(entry[start]..destination.last)
                    start = source.last + 1
                }
            }

            if (end in source) {
                add(start..<source.first)
                add(destination.first..entry[end])
                start = end + 1
                break
            }
        }

        val remaining = start..<end

        if (!remaining.isEmpty()) {
            add(remaining)
        }
    }
}
