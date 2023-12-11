package com.github.michaelbull.advent2023.day7

fun Sequence<String>.toCamelCards(): CamelCards {
    return CamelCards(map(String::toBid).toList())
}

data class CamelCards(
    val bids: List<Bid>,
) {

    fun jokerRule(): CamelCards {
        return copy(
            bids = bids.map(Bid::jokerRule)
        )
    }

    fun totalWinnings(): Long {
        return bids.sortedWith(RankComparator)
            .withIndex()
            .sumOf(::winnings)
    }

    private fun winnings(value: IndexedValue<Bid>): Long {
        val (index, bid) = value
        val rank = index + 1
        return bid.amount * rank
    }
}
