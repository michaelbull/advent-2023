package com.github.michaelbull.advent2023.day17

import com.github.michaelbull.advent2023.math.Vector2
import com.github.michaelbull.advent2023.math.Vector2IntMap
import com.github.michaelbull.advent2023.math.toVector2IntMap
import java.util.PriorityQueue

fun Sequence<String>.toCityMap(): CityMap {
    return CityMap(this.toVector2IntMap())
}

data class CityMap(
    val map: Vector2IntMap,
) {

    fun minHeatLoss(steps: IntRange): Int {
        val cumulativeHeatLossByMovement = mutableMapOf<Movement, Int>().withDefault { Int.MAX_VALUE }
        val movements = PriorityQueue(compareBy(cumulativeHeatLossByMovement::get))

        fun visit(movement: Movement, cumulativeHeatLoss: Int = 0) {
            cumulativeHeatLossByMovement[movement] = cumulativeHeatLoss
            movements += movement
        }

        INITIAL_MOVEMENTS.forEach(::visit)

        while (movements.isNotEmpty()) {
            val movement = movements.poll()
            val cumulativeHeatLoss = cumulativeHeatLossByMovement.getValue(movement)

            if (movement.cityBlock == map.last) {
                return cumulativeHeatLoss
            } else {
                for ((potentialMovement, potentialHeatLoss) in movement.potentialMovements(steps)) {
                    val currentCumulativeHeatLoss = cumulativeHeatLossByMovement.getValue(potentialMovement)
                    val potentialCumulativeHeatLoss = cumulativeHeatLoss + potentialHeatLoss

                    if (potentialCumulativeHeatLoss < currentCumulativeHeatLoss) {
                        visit(potentialMovement, potentialCumulativeHeatLoss)
                    }
                }
            }
        }

        throw IllegalArgumentException()
    }

    private fun Movement.potentialMovements(steps: IntRange): Sequence<PotentialMovement> = sequence {
        var potentialHeatLoss = 0
        var potentialCityBlock = cityBlock

        for (step in 1..steps.last) {
            potentialCityBlock += direction
            potentialHeatLoss += map.getOrNull(potentialCityBlock) ?: break

            if (step >= steps.first) {
                val movements = ROTATIONS.map { rotate ->
                    val rotatedDirection = rotate(direction)
                    val movement = potentialCityBlock movingIn rotatedDirection
                    PotentialMovement(movement, potentialHeatLoss)
                }

                yieldAll(movements)
            }
        }
    }

    private companion object {
        private val LAVA_POOL = Vector2.ZERO
        private val SOUTH = Vector2(+0, +1)
        private val EAST = Vector2(+1, +0)

        private val INITIAL_DIRECTIONS = setOf(
            SOUTH,
            EAST,
        )

        private val INITIAL_MOVEMENTS = INITIAL_DIRECTIONS.map { direction ->
            LAVA_POOL movingIn direction
        }

        private val ROTATIONS = setOf(
            Vector2::rotateLeft,
            Vector2::rotateRight,
        )
    }
}

private fun Vector2.rotateLeft(): Vector2 {
    return copy(
        x = y,
        y = -x
    )
}

private fun Vector2.rotateRight(): Vector2 {
    return copy(
        x = -y,
        y = x
    )
}
