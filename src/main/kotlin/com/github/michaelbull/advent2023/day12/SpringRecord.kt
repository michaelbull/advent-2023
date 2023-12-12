package com.github.michaelbull.advent2023.day12

private val CONDITION_REGEX = "[.#?]+".toRegex()
private val RECORD_REGEX = "($CONDITION_REGEX) (.*)".toRegex()

fun String.toSpringRecord(): SpringRecord {
    val result = requireNotNull(RECORD_REGEX.matchEntire(this)) {
        "$this must match $RECORD_REGEX"
    }

    val (conditionChars, damagedCountCsv) = result.destructured

    return SpringRecord(
        conditions = conditionChars.map(Char::toSpringCondition),
        contiguousDamageCounts = damagedCountCsv.split(",").map(String::toInt)
    )
}

data class SpringRecord(
    val conditions: List<SpringCondition>,
    val contiguousDamageCounts: List<Int>,
) {

    fun unfoldedArrangementCount(cache: ArrangementCountCache): Long {
        return unfold().arrangementCount(cache)
    }

    fun arrangementCount(cache: ArrangementCountCache): Long {
        return when {
            contiguousDamageCounts.isEmpty() -> {
                if (Damaged in conditions) 0 else 1
            }

            conditions.isEmpty() -> {
                0
            }

            else -> {
                cache.getOrPut(this) {
                    val condition = conditions.first()

                    val count = if (condition == Damaged) {
                        0
                    } else {
                        dropCondition().arrangementCount(cache)
                    }

                    if (countingDamaged()) {
                        count
                    } else {
                        count + dropDamaged().arrangementCount(cache)
                    }
                }
            }
        }
    }

    private fun dropCondition(): SpringRecord {
        return copy(
            conditions = conditions.drop(1)
        )
    }

    private fun dropDamaged(): SpringRecord {
        return copy(
            conditions = conditions.drop(contiguousDamageCounts.first() + 1),
            contiguousDamageCounts = contiguousDamageCounts.drop(1),
        )
    }

    private fun countingDamaged(): Boolean {
        val runLength = contiguousDamageCounts.first()
        val runExceeded = runLength > conditions.size
        val runHasRemaining = runLength < conditions.size

        val firstCondition = conditions.first()
        val run = conditions.take(runLength)

        return if (firstCondition == Operational || Operational in run) {
            true
        } else if (runExceeded) {
            true
        } else if (runHasRemaining) {
            conditions[runLength] == Damaged
        } else {
            false
        }
    }

    private fun unfold(): SpringRecord {
        return copy(
            conditions = (conditions + Unknown).repeat(5).dropLast(1),
            contiguousDamageCounts = contiguousDamageCounts.repeat(5),
        )
    }

    /** @see [CharSequence.repeat] */
    private fun <T> List<T>.repeat(n: Int): List<T> {
        require(n >= 0) { "Count 'n' must be non-negative, but was $n." }

        return when (n) {
            0 -> emptyList()
            1 -> this
            else -> when (size) {
                0 -> emptyList()
                1 -> this
                else -> List(size * n) { this[it % size] }
            }
        }
    }
}
