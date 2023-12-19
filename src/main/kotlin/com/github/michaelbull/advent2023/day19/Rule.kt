package com.github.michaelbull.advent2023.day19

private val REGEX = "([a-z]+)([><])(\\d+):([a-z]+|R|A)".toRegex()

fun String.toRule(): Rule {
    return if (this == "A") {
        Always(Accept)
    } else if (this == "R") {
        Always(Reject)
    } else if (this matches REGEX) {
        val result = requireNotNull(REGEX.matchEntire(this)) {
            "$this must match $REGEX"
        }

        val (categoryString, operatorString, operandString, workflow) = result.destructured
        val operator = operatorString.single().toOperator()
        val operand = operandString.toInt()
        val category = categoryString.single().toCategory()

        when (workflow) {
            "A" -> If(category, operator, operand, Accept)
            "R" -> If(category, operator, operand, Reject)
            else -> If(category, operator, operand, Send(workflow))
        }
    } else {
        Always(Send(this))
    }
}

sealed interface Rule {
    val instruction: Instruction
}

data class Always(
    override val instruction: Instruction,
) : Rule

data class If(
    val category: Category,
    val operator: Operator,
    val operand: Int,
    override val instruction: Instruction,
) : Rule
