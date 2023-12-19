package com.github.michaelbull.advent2023.day19

fun Sequence<String>.toSystem(): System {
    var readingWorkflows = true
    val workflows = mutableListOf<Workflow>()
    val parts = mutableListOf<Part>()

    for (line in this) {
        if (line.isEmpty()) {
            readingWorkflows = false
        } else if (readingWorkflows) {
            workflows += line.toWorkflow()
        } else {
            parts += line.toPart()
        }
    }

    return System(
        workflows = workflows.toList(),
        parts = parts.toList(),
    )
}

data class System(
    val workflows: List<Workflow>,
    val parts: List<Part>,
) {

    private val initial = workflowByName("in")

    fun acceptedRatingTotal(): Long {
        return parts.apply(initial).entries.sumOf { (part, instruction) ->
            if (instruction == Accept) {
                part.totalRating
            } else {
                0
            }
        }.toLong()
    }

    fun distinctRatingCombinationCount(range: IntRange): Long {
        val ranges = mapOf(
            ExtremelyCoolLooking to range,
            Musical to range,
            Aerodynamic to range,
            Shiny to range,
        )

        return distinctRatingCombinationCount(initial.rules, ranges)
    }

    private fun List<Part>.apply(workflow: Workflow): Map<Part, Instruction> {
        return associateWith { it.apply(workflow) }
    }

    private tailrec fun Part.apply(workflow: Workflow): Instruction {
        val rule = workflow.rules.first(::satisfies)

        return when (val instruction = rule.instruction) {
            is Accept -> instruction
            is Reject -> instruction
            is Send -> apply(workflowByName(instruction.workflow))
        }
    }

    private fun workflowByName(name: String): Workflow {
        return workflows.first { it.name == name }
    }

    private fun distinctRatingCombinationCount(rules: List<Rule>, ranges: Map<Category, IntRange>): Long {
        return when (val rule = rules.first()) {
            is Always -> {
                distinctRatingCombinationCount(rule.instruction, ranges)
            }

            is If -> {
                val (category, operator, operand, instruction) = rule
                val range = ranges.getValue(category)

                val firstTrue = when (operator) {
                    MoreThan -> maxOf(range.first, operand + 1)
                    LessThan -> range.first
                }

                val lastTrue = when (operator) {
                    MoreThan -> range.last
                    LessThan -> minOf(range.last, operand - 1)
                }

                val firstFalse = when (operator) {
                    MoreThan -> range.first
                    LessThan -> maxOf(range.first, operand)
                }

                val lastFalse = when (operator) {
                    MoreThan -> minOf(range.last, operand)
                    LessThan -> range.last
                }

                var count = 0L

                val trueRange = firstTrue..lastTrue
                if (!trueRange.isEmpty()) {
                    count += distinctRatingCombinationCount(instruction, ranges + Pair(category, trueRange))
                }

                val falseRange = firstFalse..lastFalse
                if (!falseRange.isEmpty()) {
                    count += distinctRatingCombinationCount(rules.drop(1), ranges + Pair(category, falseRange))
                }

                count
            }
        }
    }

    private fun distinctRatingCombinationCount(instruction: Instruction, ranges: Map<Category, IntRange>): Long {
        return when (instruction) {
            Accept -> ranges.values.map { range -> range.count().toLong() }.reduce(Long::times)
            Reject -> 0
            is Send -> {
                val workflow = workflowByName(instruction.workflow)
                distinctRatingCombinationCount(workflow.rules, ranges)
            }
        }
    }
}

private infix fun Part.satisfies(rule: Rule): Boolean {
    return when (rule) {
        is Always -> true
        is If -> {
            val rating = ratingIn(rule.category)

            when (rule.operator) {
                MoreThan -> rating > rule.operand
                LessThan -> rating < rule.operand
            }
        }
    }
}
