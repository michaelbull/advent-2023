package com.github.michaelbull.advent2023.day25

import com.github.michaelbull.advent2023.day25.Day25.part1
import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {

    @Test
    fun `example 1`() {
        val input = """
            jqt: rhn xhk nvd
            rsh: frs pzl lsr
            xhk: hfx
            cmg: qnr nvd lhk bvb
            rhn: xhk bvb hfx
            bvb: xhk hfx
            pzl: lsr hfx nvd
            qnr: nvd
            ntq: jqt hfx bvb xhk
            nvd: lhk
            lsr: lhk
            rzs: qnr cmg lsr rsh
            frs: qnr lhk lsr
        """

        assertEquals(54, Day25.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(543256, Day25.solve(::part1))
    }
}
