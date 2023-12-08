package com.github.michaelbull.advent2023.day2

typealias GameConfiguration = Map<CubeColor, Int>

data class Game(
    val id: Int,
    val sets: List<CubeSet>
) {

    fun isValid(configuration: GameConfiguration): Boolean {
        return configuration.all { (color, available) ->
            sets.all { it.count(color) <= available }
        }
    }

    fun minimumPower(): Int {
        return minimumConfiguration().values.reduce(Int::times)
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

    val (id, sets) = result.destructured

    return Game(
        id = id.toInt(),
        sets = sets.split(CubeSet.SEPARATOR).map(String::toCubeSet)
    )
}
