package com.github.michaelbull.advent2023.math

infix fun Vector3.downTo(to: Vector3): Vector3Progression {
    return Vector3Progression.fromClosedRange(this, to, Vector3.DOWN)
}

infix fun Vector3Progression.step(step: Vector3): Vector3Progression {
    require(step.x >= 0) { "Step X must be non-negative, was: ${step.x}." }
    require(step.y >= 0) { "Step Y must be non-negative, was: ${step.y}." }
    require(step.z >= 0) { "Step Z must be non-negative, was: ${step.z}." }

    val progressionStep = Vector3(
        x = if (this.xProgression.step > 0) step.x else -step.x,
        y = if (this.yProgression.step > 0) step.y else -step.y,
        z = if (this.zProgression.step > 0) step.z else -step.z
    )

    return Vector3Progression.fromClosedRange(first, last, progressionStep)
}

private fun dimensionProgression(rangeStart: Int, rangeEnd: Int, step: Int): IntProgression {
    return if (step == 0) {
        IntRange.EMPTY
    } else {
        IntProgression.fromClosedRange(rangeStart, rangeEnd, step)
    }
}

open class Vector3Progression(
    start: Vector3,
    endInclusive: Vector3,
    step: Vector3,
) : Iterable<Vector3> {

    init {
        require(step != Vector3.ZERO) { "Step must be non-zero." }
    }

    val xProgression = dimensionProgression(start.x, endInclusive.x, step.x)
    val yProgression = dimensionProgression(start.y, endInclusive.y, step.y)
    val zProgression = dimensionProgression(start.z, endInclusive.z, step.z)

    val first: Vector3 = Vector3(
        x = if (xProgression.isEmpty()) start.x else xProgression.first,
        y = if (yProgression.isEmpty()) start.y else yProgression.first,
        z = if (zProgression.isEmpty()) start.z else zProgression.first,
    )

    val last: Vector3 = Vector3(
        x = if (xProgression.isEmpty()) start.x else xProgression.last,
        y = if (yProgression.isEmpty()) start.y else yProgression.last,
        z = if (zProgression.isEmpty()) start.z else zProgression.last,
    )

    override fun iterator(): Iterator<Vector3> {
        return Vector3Iterator()
    }

    open fun isEmpty(): Boolean {
        return xProgression.isEmpty()
            && yProgression.isEmpty()
            && zProgression.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector3Progression

        if (xProgression != other.xProgression) return false
        if (yProgression != other.yProgression) return false
        if (zProgression != other.zProgression) return false

        return true
    }

    override fun hashCode(): Int {
        var result = xProgression.hashCode()
        result = 31 * result + yProgression.hashCode()
        result = 31 * result + zProgression.hashCode()
        return result
    }

    override fun toString(): String {
        return "x=$xProgression, y=$yProgression, z=$zProgression"
    }

    companion object {
        fun fromClosedRange(rangeStart: Vector3, rangeEnd: Vector3, step: Vector3): Vector3Progression {
            return Vector3Progression(rangeStart, rangeEnd, step)
        }
    }

    private inner class Vector3Iterator : Iterator<Vector3> {
        private var xIterator = xProgression.iterator()
        private var yIterator = yProgression.iterator()
        private var zIterator = zProgression.iterator()

        private var hasNext: Boolean = !xProgression.isEmpty() || !yProgression.isEmpty() || !zProgression.isEmpty()

        private var next = if (hasNext) {
            Vector3(
                x = if (xProgression.isEmpty()) first.x else xIterator.nextInt(),
                y = if (yProgression.isEmpty()) first.y else yIterator.nextInt(),
                z = if (zProgression.isEmpty()) first.z else zIterator.nextInt(),
            )
        } else {
            last
        }

        override fun hasNext(): Boolean = hasNext

        override fun next(): Vector3 {
            val value = next

            if (value == last) {
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

        private fun Vector3.step() = when {
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
