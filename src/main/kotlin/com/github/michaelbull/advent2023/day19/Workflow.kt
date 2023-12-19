package com.github.michaelbull.advent2023.day19

private val REGEX = "([a-z]+)\\{(.*)}".toRegex()

fun String.toWorkflow(): Workflow {
    val result = requireNotNull(REGEX.matchEntire(this)) {
        "$this must match $REGEX"
    }

    val (name, rules) = result.destructured

    return Workflow(
        name = name,
        rules = rules.split(",").map(String::toRule)
    )
}

data class Workflow(
    val name: String,
    val rules: List<Rule>,
)
