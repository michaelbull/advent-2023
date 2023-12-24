package com.github.michaelbull.advent2023.iteration

fun <T> List<T>.permutationPairs(): Sequence<Pair<T, T>> {
    return permutations(
        length = 2,
        permutation = ::permutationPair
    )
}

fun <T> List<T>.permutationTriples(): Sequence<Triple<T, T, T>> {
    return permutations(
        length = 3,
        permutation = ::permutationTriple
    )
}

fun <T> List<T>.permutations(length: Int? = null): Sequence<List<T>> {
    return permutations(
        length = length,
        permutation = ::permutationList
    )
}

inline fun <T, V> List<T>.permutations(
    length: Int? = null,
    crossinline permutation: (IntArray, Int) -> V,
): Sequence<V> = sequence {
    val count = if (length == null) {
        size
    } else {
        require(length >= 0) { "length must be non-negative but was $length" }
        length
    }

    if (size >= count) {
        val permutationIndices = IntArray(size) { it }
        val cycles = IntArray(count) { size - it }

        yield(permutation(permutationIndices, count))

        var searching = true

        while (searching) {
            var found = false
            var index = count - 1

            while (index >= 0 && !found) {
                cycles[index]--

                if (cycles[index] == 0) {
                    val indexBefore = permutationIndices[index]

                    for (i in index until size - 1) {
                        permutationIndices[i] = permutationIndices[i + 1]
                    }

                    permutationIndices[size - 1] = indexBefore
                    cycles[index] = size - index

                    index--
                } else {
                    val i = size - cycles[index]

                    permutationIndices.swapByIndex(index, i)

                    yield(permutation(permutationIndices, count))
                    found = true
                }
            }

            if (!found) {
                searching = false
            }
        }
    }
}

private fun <T> List<T>.permutationList(indices: IntArray, count: Int): List<T> {
    require(count > 0)

    return List(count) { index ->
        get(indices[index])
    }
}

private fun <T> List<T>.permutationPair(indices: IntArray, count: Int): Pair<T, T> {
    require(count == 2)

    return Pair(
        first = get(indices[0]),
        second = get(indices[1])
    )
}

private fun <T> List<T>.permutationTriple(indices: IntArray, count: Int): Triple<T, T, T> {
    require(count == 3)

    return Triple(
        first = get(indices[0]),
        second = get(indices[1]),
        third = get(indices[2]),
    )
}

fun IntArray.swapByIndex(indexA: Int, indexB: Int) {
    this[indexA] = this[indexB].also {
        this[indexB] = this[indexA]
    }
}
