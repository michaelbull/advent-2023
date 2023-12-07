package com.github.michaelbull.advent2023.day7

sealed interface HandType

data object FiveOfAKind : HandType
data object FourOfAKind : HandType
data object FullHouse : HandType
data object ThreeOfAKind : HandType
data object TwoPair : HandType
data object OnePair : HandType
data object HighCard : HandType

val HAND_TYPES_BY_STRENGTH = listOf(
    FiveOfAKind,
    FourOfAKind,
    FullHouse,
    ThreeOfAKind,
    TwoPair,
    OnePair,
    HighCard
)

fun HandType.strength(): Int {
    val index = HAND_TYPES_BY_STRENGTH.indexOf(this)
    val size = HAND_TYPES_BY_STRENGTH.size
    return size - index
}
