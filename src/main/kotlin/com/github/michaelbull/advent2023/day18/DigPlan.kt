package com.github.michaelbull.advent2023.day18

import com.github.michaelbull.advent2023.math.Vector2
import kotlin.math.absoluteValue

fun Sequence<String>.toDigPlan(): DigPlan {
    return DigPlan(this.map(String::toInstruction).toList())
}

data class DigPlan(
    val instructions: List<Instruction>,
) {

    fun lagoonCapacity(): Long {
        val vertices = instructions.runningFold(Vector2.ZERO, Vector2::plus)
        val perimeter = instructions.sumOf(Instruction::length).toLong()
        return vertices.area() + (perimeter / 2) + 1
    }

    fun fix(): DigPlan {
        return copy(instructions = instructions.map(Instruction::fix))
    }
}

private operator fun Vector2.plus(instruction: Instruction): Vector2 {
    return this + (instruction.direction.vector * instruction.length)
}

private fun List<Vector2>.area(): Long {
    return zipWithNext(Vector2::cross).sum().absoluteValue / 2
}

private fun Instruction.fix(): Instruction {
    return copy(
        direction = color.last().toDirection(),
        length = color.take(5).toInt(16)
    )
}
