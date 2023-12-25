package com.github.michaelbull.advent2023.iteration

/* pair */

fun <A, B> Iterable<A>.product(other: Iterable<B>): Sequence<Pair<A, B>> = sequence {
    for (a in this@product) {
        for (b in other) {
            yield(Pair(a, b))
        }
    }
}

fun <A, B> Pair<Iterable<A>, Iterable<B>>.product(): Sequence<Pair<A, B>> = sequence {
    for (a in first) {
        for (b in second) {
            yield(Pair(a, b))
        }
    }
}

/* triple */

fun <A, B, C> Iterable<A>.product(first: Iterable<B>, second: Iterable<C>): Sequence<Triple<A, B, C>> = sequence {
    for (a in this@product) {
        for (b in first) {
            for (c in second) {
                yield(Triple(a, b, c))
            }
        }
    }
}

fun <A, B, C> Triple<Iterable<A>, Iterable<B>, Iterable<C>>.product(): Sequence<Triple<A, B, C>> = sequence {
    for (a in first) {
        for (b in second) {
            for (c in third) {
                yield(Triple(a, b, c))
            }
        }
    }
}

/* list */

fun <T> List<List<T>>.product(): Sequence<List<T>> = sequence {
    if (isNotEmpty()) {
        val indices = IntArray(size) { 0 }
        var searching = true

        yield(product(indices))

        while (searching) {
            var found = false
            var index = indices.size - 1

            while (index >= 0 && !found) {
                indices[index]++

                if (indices[index] >= get(index).size) {
                    indices[index] = 0
                    index--
                } else {
                    yield(product(indices))
                    found = true
                }
            }

            if (!found) {
                searching = false
            }
        }
    }
}

private fun <T> List<List<T>>.product(indices: IntArray): List<T> {
    return indices.zip(this).map { (index, list) ->
        list[index]
    }
}
