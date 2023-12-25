package com.github.michaelbull.advent2023.day25

private val NAME_REGEX = "[a-z]{3}".toRegex()
private val REGEX = "($NAME_REGEX): (.*)".toRegex()

fun String.toConnection(): Connection {
    val result = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match $REGEX"
    }

    val (name, connectionsSsv) = result.destructured

    return Connection(
        name = name,
        connections = connectionsSsv.split(" "),
    )
}

data class Connection(
    val name: String,
    val connections: List<String>,
)
