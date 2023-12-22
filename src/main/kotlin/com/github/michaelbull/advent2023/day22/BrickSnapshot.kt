package com.github.michaelbull.advent2023.day22

import com.github.michaelbull.advent2023.math.Vector3

fun Sequence<String>.toBrickSnapshot(): BrickSnapshot {
    return BrickSnapshot(this.map(String::toBrick).toList())
}

data class BrickSnapshot(
    val bricks: List<Brick>,
) {

    private val bricksSortedUpwards = bricks.sortedBy(Brick::minZ)

    fun disintegratableBrickCount(): Int {
        return disintegrate().count { fallen ->
            fallen == 0
        }
    }

    fun fallenBrickCount(): Int {
        return disintegrate().sum()
    }

    private fun disintegrate(): List<Int> {
        val moved = step().map(NextBrick::brick).toList()

        return moved.map { brick ->
            val before = BrickSnapshot(moved.minusElement(brick))

            before.step().count(NextBrick::fallen)
        }
    }

    private fun step(): Sequence<NextBrick> = sequence {
        val visited = mutableSetOf<Vector3>()

        for (brick in bricksSortedUpwards) {
            val changed = brick.step(visited)
            visited += changed
            yield(NextBrick(changed, changed != brick))
        }
    }

    private fun Brick.step(visited: Set<Vector3>): Brick {
        var current = this

        while (true) {
            val moved = current.map(Vector3::moveDownwards).toSet()
            val fallen = moved.any { it in visited || it.z <= 0 }

            if (fallen) {
                return current
            } else {
                current = moved
            }
        }
    }

    private data class NextBrick(
        val brick: Brick,
        val fallen: Boolean,
    )
}

private val DOWNWARDS = Vector3(0, 0, -1)

private fun Vector3.moveDownwards(): Vector3 {
    return this + DOWNWARDS
}

private fun Iterable<Vector3>.minZ(): Int {
    return minOf(Vector3::z)
}
