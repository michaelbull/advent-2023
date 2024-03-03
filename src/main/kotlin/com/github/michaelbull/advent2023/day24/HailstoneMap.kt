package com.github.michaelbull.advent2023.day24

import com.github.michaelbull.itertools.pairCombinations

fun Sequence<String>.toHailstoneMap(): HailstoneMap {
    return HailstoneMap(map(String::toHailstone).toList())
}

data class HailstoneMap(
    val hailstones: List<Hailstone>,
) {

    fun intersections(range: ClosedFloatingPointRange<Double>): Int {
        return hailstones
            .pairCombinations()
            .count(range::contains)
    }
}

private operator fun ClosedFloatingPointRange<Double>.contains(pair: Pair<Hailstone, Hailstone>): Boolean {
    val (a, b) = pair

    val slopeDelta = b.slope - a.slope

    return if (slopeDelta == 0.0) {
        false
    } else {
        val cx = ((((b.slope * b.px) - (a.slope * a.px)) + a.py) - b.py) / slopeDelta
        val cy = (a.slope * (cx - a.px)) + a.py

        if (cx in this && cy in this) {
            a.isValid(cx, cy) && b.isValid(cx, cy)
        } else {
            false
        }
    }
}

private fun Hailstone.isValid(cx: Double, cy: Double): Boolean {
    val belowX = vx < 0 && px < cx
    val aboveX = vx > 0 && px > cx
    val belowY = vy < 0 && py < cy
    val aboveY = vy > 0 && py > cy
    return !(belowX || aboveX || belowY || aboveY)
}
