package com.github.michaelbull.advent2023.day15

sealed interface LensOperation {
    val label: String
}

data class RemoveLens(
    override val label: String,
) : LensOperation

data class ReplaceLens(
    override val label: String,
    val focalLength: Int,
) : LensOperation

private val LABEL_REGEX = "[a-z]+".toRegex()
private val FOCAL_LENGTH_REGEX = "[1-9]".toRegex()
private val REMOVE_REGEX = "($LABEL_REGEX)-".toRegex()
private val REPLACE_REGEX = "($LABEL_REGEX)=($FOCAL_LENGTH_REGEX)".toRegex()

fun String.toLensOperation(): LensOperation {
    return if (this matches REMOVE_REGEX) {
        val (label) = requireNotNull(REMOVE_REGEX.matchEntire(this)).destructured
        RemoveLens(label)
    } else if (this matches REPLACE_REGEX) {
        val (label, focalLength) = requireNotNull(REPLACE_REGEX.matchEntire(this)).destructured
        ReplaceLens(label, focalLength.toInt())
    } else {
        throw IllegalArgumentException("$this is not a lens operation")
    }
}
