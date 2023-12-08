package com.github.michaelbull.advent2023.day8

import com.github.michaelbull.advent2023.math.leastCommonMultiple

private val NODE_REGEX = "[A-Z0-9]{3}".toRegex()
private val NETWORK_REGEX = "($NODE_REGEX) = \\(($NODE_REGEX), ($NODE_REGEX)\\)".toRegex()

fun Sequence<String>.toDesertMap(): DesertMap {
    var instructions = emptyList<Instruction>()
    val network = mutableMapOf<Node, List<Node>>()

    for ((index, line) in withIndex()) {
        if (index == 0) {
            instructions = line.map(Char::toInstruction)
        } else if (index > 1) {
            val result = requireNotNull(NETWORK_REGEX.matchEntire(line)) {
                "$line must match $NETWORK_REGEX"
            }

            val (_, from, left, right) = result.groupValues

            network[Node(from)] = listOf(Node(left), Node(right))
        }
    }

    return DesertMap(
        instructions = instructions,
        network = network.toMap()
    )
}

data class DesertMap(
    val instructions: List<Instruction>,
    val network: Map<Node, List<Node>>,
) {

    fun stepCount(start: Node, end: Node): Long {
        return stepCount(start) { node ->
            node == end
        }
    }

    fun stepCount(isStart: (Node) -> Boolean, isEnd: (Node) -> Boolean): Long {
        val counts = network.keys
            .filter(isStart)
            .map { start -> stepCount(start, isEnd) }

        return counts.fold(1L, ::leastCommonMultiple)
    }

    private fun stepCount(start: Node, isEnd: (Node) -> Boolean): Long {
        return traverse(start, isEnd)
            .count()
            .toLong()
    }

    private fun traverse(start: Node, isEnd: (Node) -> Boolean) = sequence {
        var cur = start

        for (instruction in instructions.asSequence().repeating()) {
            cur = cur.next(instruction)
            yield(cur)

            if (isEnd(cur)) {
                break
            }
        }
    }

    private fun Node.next(instruction: Instruction): Node {
        val (left, right) = network.getValue(this)

        return when (instruction) {
            Left -> left
            Right -> right
        }
    }

    private fun <T> Sequence<T>.repeating() = sequence {
        while (true) {
            yieldAll(this@repeating)
        }
    }
}
