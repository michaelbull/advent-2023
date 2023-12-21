package com.github.michaelbull.advent2023.day21

import com.github.michaelbull.advent2023.Puzzle
import com.github.michaelbull.advent2023.math.Vector3

object Day21 : Puzzle<GardenMap, Long>(day = 21) {

    override fun parse(lines: Sequence<String>): GardenMap {
        return lines.toGardenMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: GardenMap): Long {
        return input.reachablePlots(64).toLong()
    }

    fun part2(input: GardenMap): Long {
        val target = (26501365 - input.start.x) / input.map.width
        return target.toLong().quadratic(POLYNOMIAL)
    }

    /**
     * Lagrange's Interpolation formula for ax^2 + bx + c with x=[0,1,2] and y=[y0,y1,y2] we have
     *   f(x) = (x^2-3x+2) * y0/2 - (x^2-2x)*y1 + (x^2-x) * y2/2
     * so the coefficients are:
     * a = y0/2 - y1 + y2/2
     * b = -3*y0/2 + 2*y1 - y2/2
     * c = y0
     *
     * [Reddit](https://www.reddit.com/r/adventofcode/comments/18nevo3/2023_day_21_solutions/keb8ud3/)
     */
    private fun Vector3.lagrange(): Vector3 {
        return Vector3(
            x = (x / 2.0 - y + z / 2.0).toInt(),
            y = (-3 * (x / 2.0) + 2 * y - z / 2.0).toInt(),
            z = x,
        )
    }

    private fun Long.quadratic(polynomial: Vector3): Long {
        val (a, b, c) = polynomial
        return (a * this * this) + (b * this) + c
    }

    /**
     * input.reachablePlots(input.start.x)
     * input.reachablePlots(input.start.x + input.map.width)
     * input.reachablePlots(input.start.x + input.map.width + input.map.width)
     */
    private val POLYNOMIAL = Vector3(3943, 35126, 97407).lagrange()
}
