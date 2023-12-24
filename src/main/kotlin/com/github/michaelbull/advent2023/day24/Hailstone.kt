package com.github.michaelbull.advent2023.day24

private val NUMBER = "-?[0-9]+".toRegex()
private val REGEX = Regex("($NUMBER),\\s*($NUMBER),\\s*($NUMBER)\\s*@\\s*($NUMBER),\\s*($NUMBER),\\s*($NUMBER)")

fun String.toHailstone(): Hailstone {
    val result = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match $REGEX"
    }

    val (px, py, pz, vx, vy, vz) = result.destructured

    return Hailstone(
        px = px.toDouble(),
        py = py.toDouble(),
        pz = pz.toDouble(),
        vx = vx.toDouble(),
        vy = vy.toDouble(),
        vz = vz.toDouble(),
    )
}

data class Hailstone(
    val px: Double,
    val py: Double,
    val pz: Double,
    val vx: Double,
    val vy: Double,
    val vz: Double,
) {
    val slope = vy / vx
}
