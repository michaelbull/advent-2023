package com.github.michaelbull.advent2023.math

class Vector3ProgressionIterator(
    first: Vector3,
    last: Vector3,
    val step: Vector3,
) : Iterator<Vector3> {

    private val xProgression = IntProgression.fromClosedRange(first.x, last.x, step.x)
    private val yProgression = IntProgression.fromClosedRange(first.y, last.y, step.y)
    private val zProgression = IntProgression.fromClosedRange(first.z, last.z, step.z)

    private val finalElement = Vector3(xProgression.last, yProgression.last, zProgression.last)

    private var xIterator = xProgression.iterator()
    private var yIterator = yProgression.iterator()
    private var zIterator = zProgression.iterator()

    private var hasNext: Boolean = !xProgression.isEmpty() && !yProgression.isEmpty() && !zProgression.isEmpty()
    private var next =
        if (hasNext) Vector3(xIterator.nextInt(), yIterator.nextInt(), zIterator.nextInt()) else finalElement

    override fun hasNext(): Boolean = hasNext

    override fun next(): Vector3 {
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

    private fun Vector3.step(): Vector3 {
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

            zIterator.hasNext() -> {
                when {
                    xProgression.isEmpty() && yProgression.isEmpty() -> {
                        copy(
                            z = zIterator.nextInt()
                        )
                    }

                    xProgression.isEmpty() -> {
                        yIterator = yProgression.iterator()

                        copy(
                            y = yIterator.nextInt(),
                            z = zIterator.nextInt(),
                        )
                    }

                    yProgression.isEmpty() -> {
                        xIterator = xProgression.iterator()

                        copy(
                            x = xIterator.nextInt(),
                            z = zIterator.nextInt(),
                        )
                    }

                    else -> {
                        xIterator = xProgression.iterator()
                        yIterator = yProgression.iterator()

                        copy(
                            x = xIterator.nextInt(),
                            y = yIterator.nextInt(),
                            z = zIterator.nextInt(),
                        )
                    }
                }
            }

            else -> throw NoSuchElementException()
        }
    }
}
