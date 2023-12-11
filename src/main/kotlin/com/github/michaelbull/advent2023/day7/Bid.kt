package com.github.michaelbull.advent2023.day7

fun String.toBid(): Bid {
    val (hand, amount) = split(" ")

    return Bid(
        hand = hand.toHand(),
        amount = amount.toLong(),
    )
}

data class Bid(
    val hand: Hand,
    val amount: Long,
) {
    fun jokerRule(): Bid {
        return copy(
            hand = hand.jokerRule(),
        )
    }
}
