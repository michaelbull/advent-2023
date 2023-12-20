package com.github.michaelbull.advent2023.math

import kotlin.math.abs

/* https://en.wikipedia.org/wiki/Greatest_common_divisor */

tailrec fun greatestCommonDivisor(a: Long, b: Long): Long {
    return if (b == 0L) {
        a
    } else {
        greatestCommonDivisor(b, a % b)
    }
}

fun Iterable<Long>.greatestCommonDivisor(): Long {
    return reduce(::greatestCommonDivisor)
}

fun Sequence<Long>.greatestCommonDivisor(): Long {
    return reduce(::greatestCommonDivisor)
}

fun leastCommonMultiple(a: Long, b: Long): Long {
    return abs(a * b) / greatestCommonDivisor(a, b)
}

fun Iterable<Long>.leastCommonMultiple(): Long {
    return reduce(::leastCommonMultiple)
}

fun Sequence<Long>.leastCommonMultiple(): Long {
    return reduce(::leastCommonMultiple)
}

/* https://en.wikipedia.org/wiki/Triangular_number */

fun triangular(n: Int): Int {
    return (n * (n + 1)) / 2
}

fun triangular(n: Long): Long {
    return (n * (n + 1)) / 2
}
