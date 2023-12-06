package com.github.michaelbull.advent2023.day6

fun Sequence<String>.toRaces(): Sequence<Race> {
    val (times, distances) = raceData()

    return times.findNumbers()
        .zip(distances.findNumbers())
        .map(Pair<Long, Long>::toRace)
}

fun Sequence<String>.toRace(): Race {
    val (times, distances) = raceData()

    return Race(
        time = times.toMergedNumber(),
        distance = distances.toMergedNumber()
    )
}

data class Race(
    val time: Long,
    val distance: Long,
) {

    fun winningCount(): Int {
        val speeds = 1..<time
        return speeds.count(::isWinningSpeed)
    }

    private fun isWinningSpeed(speed: Long): Boolean {
        val remaining = time - speed
        val travelled = speed * remaining
        return travelled > distance
    }
}

private val TIME_REGEX = "Time: (.*)".toRegex()
private val DISTANCE_REGEX = "Distance: (.*)".toRegex()
private val NUMBER_REGEX = "\\d+".toRegex()
private val WHITESPACE_REGEX = "\\s+".toRegex()

private fun String.findNumbers(): Sequence<Long> {
    return NUMBER_REGEX
        .findAll(this)
        .map { it.value.toLong() }
}

private fun Sequence<String>.raceData(): Pair<String, String> {
    val (timesLine, distancesLine) = chunked(2).single()

    val timesResult = requireNotNull(TIME_REGEX.matchEntire(timesLine)) {
        "$this must match $TIME_REGEX"
    }

    val distancesResult = requireNotNull(DISTANCE_REGEX.matchEntire(distancesLine)) {
        "$this must match $DISTANCE_REGEX"
    }

    return timesResult.groupValues[1] to distancesResult.groupValues[1]
}

private fun String.toMergedNumber(): Long {
    return replace(WHITESPACE_REGEX, "").toLong()
}

private fun Pair<Long, Long>.toRace(): Race {
    return Race(first, second)
}
