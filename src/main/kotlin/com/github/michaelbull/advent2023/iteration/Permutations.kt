package com.github.michaelbull.advent2023.iteration

/* pair */

fun <T> List<T>.permutationPairs(): Sequence<Pair<T, T>> {
    return permutations(
        length = 2,
        permutation = ::permutationPair
    )
}

private fun <T> List<T>.permutationPair(indices: IntArray, count: Int): Pair<T, T> {
    require(count == 2)

    return Pair(
        first = get(indices[0]),
        second = get(indices[1])
    )
}

/* triple */

fun <T> List<T>.permutationTriples(): Sequence<Triple<T, T, T>> {
    return permutations(
        length = 3,
        permutation = ::permutationTriple
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

/* list */

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

    val count = length ?: size

    require(count >= 0) { "length must be non-negative but was $length" }

    if (count in 1..size) {
        val indices = IntArray(size) { it }
        val cycles = IntArray(count) { size - it }
        var searching = true

        yield(permutation(indices, count))

        while (searching) {
            var found = false
            var index = count - 1

            while (index >= 0 && !found) {
                cycles[index]--

                if (cycles[index] == 0) {
                    val indexBefore = indices[index]

                    for (i in index until size - 1) {
                        indices[i] = indices[i + 1]
                    }

                    indices[size - 1] = indexBefore
                    cycles[index] = size - index
                    index--
                } else {
                    val i = size - cycles[index]

                    indices.swapByIndex(index, i)

                    yield(permutation(indices, count))
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

fun IntArray.swapByIndex(a: Int, b: Int) {
    this[a] = this[b].also {
        this[b] = this[a]
    }
}
