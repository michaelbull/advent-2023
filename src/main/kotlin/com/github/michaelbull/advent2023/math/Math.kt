package com.github.michaelbull.advent2023.math

import kotlin.math.abs

/* https://en.wikipedia.org/wiki/Greatest_common_divisor */

tailrec fun greatestCommonDivisor(a: Long, b: Long): Long {
    return if (a == 0L) {
        abs(b)
    } else {
        greatestCommonDivisor(b % a, a)
    }
}

fun leastCommonMultiple(a: Long, b: Long): Long {
    return (a * b) / greatestCommonDivisor(a, b)
}

/* https://en.wikipedia.org/wiki/Triangular_number */

fun triangular(n: Int): Int {
    return (n * (n + 1)) / 2
}

fun triangular(n: Long): Long {
    return (n * (n + 1)) / 2
}
