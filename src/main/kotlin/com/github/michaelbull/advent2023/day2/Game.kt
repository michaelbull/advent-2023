package com.github.michaelbull.advent2023.day2

typealias GameConfiguration = Map<CubeColor, Int>

data class Game(
    val id: Int,
    val sets: List<CubeSet>
) {
    fun isValid(configuration: GameConfiguration): Boolean {
        for ((color, available) in configuration) {
            for (set in sets) {
                if (set.count(color) > available) {
                    return false
                }
            }
        }

        return true
    }

    fun minimumPower(): Int {
        return minimumConfiguration().values.reduce { acc, count ->
            acc * count
        }
    }

    private fun minimumConfiguration(): GameConfiguration {
        return CUBE_COLORS.associateWith { color ->
            sets.maxOf { set -> set.count(color) }
        }
    }

    override fun toString(): String {
        return "Game $id: ${sets.joinToString(CubeSet.SEPARATOR)}"
    }
}

private val REGEX = "Game (\\d+): (.*)".toRegex()

fun String.toGame(): Game {
    val result = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match $REGEX"
    }

    val (_, id, sets) = result.groupValues

    return Game(
        id = id.toInt(),
        sets = sets.split(CubeSet.SEPARATOR).map(String::toCubeSet)
    )
}
