package com.github.michaelbull.advent2023.iteration

/* pair */

fun <T> List<T>.combinationPairs(): Sequence<Pair<T, T>> {
    return combinations(
        length = 2,
        combination = ::combinationPair
    )
}

private fun <T> List<T>.combinationPair(indices: IntArray, count: Int): Pair<T, T> {
    require(count == 2)

    return Pair(
        first = get(indices[0]),
        second = get(indices[1]),
    )
}

/* triple */

fun <T> List<T>.combinationTriples(): Sequence<Triple<T, T, T>> {
    return combinations(
        length = 3,
        combination = ::combinationTriple
    )
}

private fun <T> List<T>.combinationTriple(indices: IntArray, count: Int): Triple<T, T, T> {
    require(count == 3)

    return Triple(
        first = get(indices[0]),
        second = get(indices[1]),
        third = get(indices[2]),
    )
}

/* list */

fun <T> List<T>.combinations(length: Int? = null): Sequence<List<T>> {
    return combinations(
        length = length,
        combination = ::combinationList
    )
}

inline fun <T, V> List<T>.combinations(
    length: Int? = null,
    crossinline combination: (indices: IntArray, count: Int) -> V,
): Sequence<V> = sequence {

    val count = length ?: size

    require(count >= 0) { "length must be non-negative but was $length" }

    if (count in 1..size) {
        val indices = IntArray(count) { it }
        var searching = true

        yield(combination(indices, count))

        while (searching) {
            var found = false
            var index = count - 1

            while (index >= 0 && !found) {
                if (indices[index] == index + size - count) {
                    index--
                } else {
                    indices[index]++

                    for (j in index + 1 until count) {
                        indices[j] = indices[j - 1] + 1
                    }

                    yield(combination(indices, count))
                    found = true
                }
            }

            if (!found) {
                searching = false
            }
        }
    }
}

private fun <T> List<T>.combinationList(indices: IntArray, count: Int): List<T> {
    require(count > 0)

    return indices.map(this::get)
}
