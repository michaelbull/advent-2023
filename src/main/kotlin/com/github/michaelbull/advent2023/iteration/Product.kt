package com.github.michaelbull.advent2023.iteration

fun <A, B> Pair<Iterable<A>, Iterable<B>>.product(): Sequence<Pair<A, B>> = sequence {
    for (a in first) {
        for (b in second) {
            yield(Pair(a, b))
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

fun <T> List<List<T>>.product(): Sequence<List<T>> = sequence {
    if (isNotEmpty()) {
        val productIndices = IntArray(size) { 0 }

        yield(product(productIndices))

        var searching = true

        while (searching) {
            var found = false
            var index = productIndices.size - 1

            while (index >= 0 && !found) {
                productIndices[index]++

                if (productIndices[index] >= this@product[index].size) {
                    productIndices[index] = 0
                } else {
                    yield(product(productIndices))
                    found = true
                }

                index--
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
