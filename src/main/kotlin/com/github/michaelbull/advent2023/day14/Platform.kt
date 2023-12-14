package com.github.michaelbull.advent2023.day14

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.Vector2CharMap
import com.github.michaelbull.advent2023.math.toVector2CharMap

fun Sequence<String>.toPlatform(): Platform {
    return Platform(this.toVector2CharMap())
}

data class Platform(
    val map: Vector2CharMap,
) {

    fun tiltNorth() = tiltY(+1)
    fun tiltEast() = tiltX(-1)
    fun tiltSouth() = tiltY(-1)
    fun tiltWest() = tiltX(+1)

    fun totalLoad(): Int {
        return map.positions()
            .filter(map::isRoundRockAt)
            .sumOf(map::load)
    }

    fun cycle(times: Int): Sequence<Platform> = sequence {
        val cycles = mutableMapOf<Platform, Int>()

        for ((cycle, pair) in cycleSequence().zipWithNext().withIndex()) {
            val (platform, nextPlatform) = pair

            cycles[platform] = cycle
            yield(nextPlatform)

            val nextPlatformsCycle = cycles[nextPlatform]

            if (nextPlatformsCycle != null) {
                val nextCycle = cycle + 1
                val delta = nextCycle - nextPlatformsCycle

                if (delta > 0) {
                    val remainingCycles = times - cycle
                    val remaining = remainingCycles % delta
                    yieldAll(nextPlatform.cycleSequence().take(remaining))
                }

                break
            }
        }
    }

    private fun cycle(): Platform {
        return tiltNorth()
            .tiltWest()
            .tiltSouth()
            .tiltEast()
    }

    private fun cycleSequence(): Sequence<Platform> {
        return generateSequence(this, Platform::cycle)
    }

    private fun tilt(delta: Int, axis: Axis): Platform {
        val tilted = Vector2CharMap(width = map.width, height = map.height) { '.' }
        val range = axis.range.sortedBy(delta::times)

        for (b in axis.opposite) {
            var emptyA = range.first()

            for (a in range) {
                val position = axis(a, b)
                val char = map[position]

                if (char.isRoundRock()) {
                    val emptyPosition = axis(emptyA, b)
                    tilted[emptyPosition] = 'O'
                    emptyA += delta
                } else if (char.isCubeRock()) {
                    tilted[position] = '#'
                    emptyA = a + delta
                }
            }
        }

        return copy(map = tilted)
    }

    private fun tiltX(x: Int): Platform {
        return tilt(x, HorizontalAxis(map))
    }

    private fun tiltY(y: Int): Platform {
        return tilt(y, VerticalAxis(map))
    }
}

private fun Vector2CharMap.load(vector2: Vector2): Int {
    return height - vector2.y
}

private fun Char.isRoundRock(): Boolean {
    return this == 'O'
}

private fun Char.isCubeRock(): Boolean {
    return this == '#'
}

private fun Vector2CharMap.isRoundRockAt(position: Vector2): Boolean {
    return get(position).isRoundRock()
}
