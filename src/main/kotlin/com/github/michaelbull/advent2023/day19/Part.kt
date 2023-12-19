package com.github.michaelbull.advent2023.day19

private val REGEX = "\\{(.*)}".toRegex()

fun String.toPart(): Part {
    val result = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match $REGEX"
    }

    val (ratingsCsv) = result.destructured

    val ratingsByCategory = ratingsCsv.split(",").associate { pair ->
        val (category, rating) = pair.split("=")
        category.single().toCategory() to rating.toInt()
    }

    return Part(
        x = ratingsByCategory.getValue(ExtremelyCoolLooking),
        m = ratingsByCategory.getValue(Musical),
        a = ratingsByCategory.getValue(Aerodynamic),
        s = ratingsByCategory.getValue(Shiny),
    )
}

data class Part(
    val x: Int,
    val m: Int,
    val a: Int,
    val s: Int,
) {

    val totalRating = x + m + a + s

    fun ratingIn(category: Category): Int {
        return when (category) {
            ExtremelyCoolLooking -> x
            Musical -> m
            Aerodynamic -> a
            Shiny -> s
        }
    }
}
