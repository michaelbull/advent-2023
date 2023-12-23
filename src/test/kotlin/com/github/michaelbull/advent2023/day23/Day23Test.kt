package com.github.michaelbull.advent2023.day23

import com.github.michaelbull.advent2023.day23.Day23.part1
import com.github.michaelbull.advent2023.day23.Day23.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {

    @Test
    fun `example 1`() {
        assertEquals(94, Day23.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(154, Day23.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(2106, Day23.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(6350, Day23.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            #.#####################
            #.......#########...###
            #######.#########.#.###
            ###.....#.>.>.###.#.###
            ###v#####.#v#.###.#.###
            ###.>...#.#.#.....#...#
            ###v###.#.#.#########.#
            ###...#.#.#.......#...#
            #####.#.#.#######.#.###
            #.....#.#.#.......#...#
            #.#####.#.#.#########v#
            #.#...#...#...###...>.#
            #.#.#v#######v###.###v#
            #...#.>.#...>.>.#.###.#
            #####v#.#.###v#.#.###.#
            #.....#...#...#.#.#...#
            #.#########.###.#.#.###
            #...###...#...#...#.###
            ###.###.#.###v#####v###
            #...#...#.#.>.>.#.>.###
            #.###.###.#.###.#.#v###
            #.....###...###...#...#
            #####################.#
        """
    }
}
