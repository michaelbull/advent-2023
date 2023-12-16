package com.github.michaelbull.advent2023.day15

private typealias Box = MutableMap<String, Int>

fun Iterable<String>.toLensConfiguration(): LensConfiguration {
    val boxes = List(256) { mutableMapOf<String, Int>() }

    for (step in this) {
        val operation = step.toLensOperation()
        val label = operation.label
        val correctBox = boxes[label.hash()]

        when (operation) {
            is ReplaceLens -> correctBox[label] = operation.focalLength
            is RemoveLens -> correctBox -= label
        }
    }

    return LensConfiguration(boxes)
}

data class LensConfiguration(
    val boxes: List<Box>,
) {

    fun focusingPower(): Int {
        return boxes.withIndex().sumOf(::focusingPower)
    }

    private fun focusingPower(indexedBox: IndexedValue<Box>): Int {
        val (boxNumber, box) = indexedBox

        fun focusingPower(indexedLens: IndexedValue<Int>): Int {
            val (lensIndex, focalLength) = indexedLens
            val lensSlot = lensIndex + 1
            return (1 + boxNumber) * lensSlot * focalLength
        }

        return box.values.withIndex().sumOf(::focusingPower)
    }

}
