package com.github.michaelbull.advent2023.day5

fun Iterable<AlmanacMap>.lookup(index: Long): Long {
    return fold(index) { acc, map ->
        map[acc]
    }
}

data class AlmanacMap(
    val source: String,
    val destination: String,
    val entries: List<AlmanacEntry>
) {

    operator fun get(key: Long): Long {
        return entries
            .firstNotNullOfOrNull { entry -> entry[key] }
            ?: key
    }

    fun reverse(): AlmanacMap {
        return copy(
            source = destination,
            destination = source,
            entries = entries.map(AlmanacEntry::reverse)
        )
    }
}
