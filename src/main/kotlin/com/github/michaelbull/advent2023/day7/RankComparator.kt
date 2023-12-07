package com.github.michaelbull.advent2023.day7

object RankComparator : Comparator<Bid> {

    private fun compare(a: Hand, b: Hand): Int {
        val (cardsA, cardsB) = a.cards
            .zip(b.cards)
            .first { (a, b) -> a != b }

        return cardsA.strength() - cardsB.strength()
    }

    override fun compare(a: Bid, b: Bid): Int {
        val strengthDelta = a.hand.type.strength() - b.hand.type.strength()
        val sameStrength = strengthDelta == 0

        return if (sameStrength) {
            compare(a.hand, b.hand)
        } else {
            strengthDelta
        }
    }
}
