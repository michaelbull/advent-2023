package com.github.michaelbull.advent2023.iteration

fun <T> List<T>.combinationPairs(): Sequence<Pair<T, T>> {
    return combinations(
        length = 2,
        combination = ::combinationPair
    )
}

fun <T> List<T>.combinationTriples(): Sequence<Triple<T, T, T>> {
    return combinations(
        length = 3,
        combination = ::combinationTriple
    )
}

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

    val count = if (length == null) {
        size
    } else {
        require(length >= 0) { "length must be non-negative but was $length" }
        length
    }

    if (size >= count) {
        val combinationIndices = IntArray(count) { it }

        yield(combination(combinationIndices, count))

        var index = combinationIndices.lastIndex(count, size)
        while (index >= 0) {
            combinationIndices[index]++

            for (i in index + 1 until count) {
                combinationIndices[i] = combinationIndices[i - 1] + 1
            }

            yield(combination(combinationIndices, count))
            index = combinationIndices.lastIndex(count, size)
        }
    }
}

private fun <T> List<T>.combinationList(indices: IntArray, count: Int): List<T> {
    require(count > 0)

    return indices.map(this::get)
}

private fun <T> List<T>.combinationPair(indices: IntArray, count: Int): Pair<T, T> {
    require(count == 2)

    return Pair(
        first = get(indices[0]),
        second = get(indices[1]),
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

fun IntArray.lastIndex(length: Int, size: Int): Int {
    var index = length - 1
    while (index >= 0 && get(index) == index + size - length) {
        index--
    }
    return index
}
