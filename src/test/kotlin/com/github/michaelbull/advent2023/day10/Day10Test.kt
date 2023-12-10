package com.github.michaelbull.advent2023.day10

import com.github.michaelbull.advent2023.day10.Day10.part1
import com.github.michaelbull.advent2023.day10.Day10.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    @Test
    fun `example 1`() {
        val input = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
        """

        assertEquals(4, Day10.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            ..F7.
            .FJ|.
            SJ.L7
            |F--J
            LJ...
        """

        assertEquals(8, Day10.solve(::part1, input))
    }

    @Test
    fun `example 3`() {
        val input = """
            ...........
            .S-------7.
            .|F-----7|.
            .||.....||.
            .||.....||.
            .|L-7.F-J|.
            .|..|.|..|.
            .L--J.L--J.
            ...........
        """

        assertEquals(4, Day10.solve(::part2, input))
    }

    @Test
    fun `example 4`() {
        val input = """
            .F----7F7F7F7F-7....
            .|F--7||||||||FJ....
            .||.FJ||||||||L7....
            FJL7L7LJLJ||LJ.L-7..
            L--J.L7...LJS7F-7L7.
            ....F-J..F7FJ|L7L7L7
            ....L7.F7||L7|.L7L7|
            .....|FJLJ|FJ|F7|.LJ
            ....FJL-7.||.||||...
            ....L---J.LJ.LJLJ...
        """

        assertEquals(8, Day10.solve(::part2, input))
    }

    @Test
    fun `example 5`() {
        val input = """
            FF7FSF7F7F7F7F7F---7
            L|LJ||||||||||||F--J
            FL-7LJLJ||||||LJL-77
            F--JF--7||LJLJ7F7FJ-
            L---JF-JLJ.||-FJLJJ7
            |F|F-JF---7F7-L7L|7|
            |FFJF7L7F-JF7|JL---7
            7-L-JL7||F7|L7F-7F7|
            L.L7LFJ|||||FJL7||LJ
            L7JLJL-JLJLJL--JLJ.L
        """

        assertEquals(10, Day10.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(6806, Day10.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(449, Day10.solve(::part2))
    }
}
