package com.github.michaelbull.advent2023.day7

data class Bid(
    val hand: Hand,
    val amount: Long,
)

fun String.toBid(): Bid {
    val (hand, amount) = split(" ")

    return Bid(
        hand = hand.toHand(),
        amount = amount.toLong(),
    )
}

fun String.toJokerBid(): Bid {
    val (hand, amount) = split(" ")

    return Bid(
        hand = hand.toHandWithJokers(),
        amount = amount.toLong(),
    )
}
