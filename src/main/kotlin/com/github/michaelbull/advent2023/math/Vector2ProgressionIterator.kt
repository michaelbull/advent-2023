package com.github.michaelbull.advent2023.math

class Vector2ProgressionIterator(
    first: Vector2,
    last: Vector2,
    val step: Vector2,
) : Iterator<Vector2> {

    private val xProgression = IntProgression.fromClosedRange(first.x, last.x, step.x)
    private val yProgression = IntProgression.fromClosedRange(first.y, last.y, step.y)

    private val finalElement = Vector2(xProgression.last, yProgression.last)

    private var xIterator = xProgression.iterator()
    private var yIterator = yProgression.iterator()

    private var hasNext: Boolean = !xProgression.isEmpty() && !yProgression.isEmpty()
    private var next = if (hasNext) Vector2(xIterator.nextInt(), yIterator.nextInt()) else finalElement

    override fun hasNext(): Boolean = hasNext

    override fun next(): Vector2 {
        val value = next

        if (value == finalElement) {
            if (hasNext) {
                hasNext = false
            } else {
                throw NoSuchElementException()
            }
        } else {
            next = next.step()
        }

        return value
    }

    private fun Vector2.step(): Vector2 {
        return when {
            xIterator.hasNext() -> {
                copy(
                    x = xIterator.nextInt()
                )
            }

            yIterator.hasNext() -> when {
                xProgression.isEmpty() -> {
                    copy(
                        y = yIterator.nextInt()
                    )
                }

                else -> {
                    xIterator = xProgression.iterator()

                    copy(
                        x = xIterator.nextInt(),
                        y = yIterator.nextInt(),
                    )
                }
            }

            else -> throw NoSuchElementException()
        }
    }
}
