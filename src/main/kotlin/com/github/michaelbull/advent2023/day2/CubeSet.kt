package com.github.michaelbull.advent2023.day2

data class CubeSet(
    val counts: List<CubeCount>
) {
    fun count(color: CubeColor): Int {
        return counts
            .filter { it.color == color }
            .sumOf(CubeCount::count)
    }

    override fun toString(): String {
        return counts.joinToString(CubeCount.SEPARATOR)
    }

    companion object {
        const val SEPARATOR = "; "
    }
}

fun String.toCubeSet(): CubeSet {
    return CubeSet(
        counts = split(CubeCount.SEPARATOR).map(String::toCubeCount)
    )
}
