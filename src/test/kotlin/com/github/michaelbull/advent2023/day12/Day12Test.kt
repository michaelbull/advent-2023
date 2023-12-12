package com.github.michaelbull.advent2023.day12

import com.github.michaelbull.advent2023.day12.Day12.part1
import com.github.michaelbull.advent2023.day12.Day12.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    @Test
    fun `example 1`() {
        assertEquals(21, Day12.solve(::part1, EXAMPLE))
    }

    @Test
    fun `example 2`() {
        assertEquals(525152, Day12.solve(::part2, EXAMPLE))
    }

    @Test
    fun `answer 1`() {
        assertEquals(7260, Day12.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(1909291258644, Day12.solve(::part2))
    }

    private companion object {
        private const val EXAMPLE = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
        """
    }
}
