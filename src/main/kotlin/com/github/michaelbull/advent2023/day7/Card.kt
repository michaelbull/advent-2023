package com.github.michaelbull.advent2023.day7

sealed interface Card

data object Ace : Card {
    override fun toString(): String = "A"
}

data object King : Card {
    override fun toString(): String = "K"
}

data object Queen : Card {
    override fun toString(): String = "Q"
}

data object Jack : Card {
    override fun toString(): String = "J"
}

data object Ten : Card {
    override fun toString(): String = "T"
}

data object Nine : Card {
    override fun toString(): String = "9"
}

data object Eight : Card {
    override fun toString(): String = "8"
}

data object Seven : Card {
    override fun toString(): String = "7"
}

data object Six : Card {
    override fun toString(): String = "6"
}

data object Five : Card {
    override fun toString(): String = "5"
}

data object Four : Card {
    override fun toString(): String = "4"
}

data object Three : Card {
    override fun toString(): String = "3"
}

data object Two : Card {
    override fun toString(): String = "2"
}

data object Joker : Card {
    override fun toString(): String = "J"
}

val CARDS_BY_STRENGTH = listOf(
    Ace,
    King,
    Queen,
    Jack,
    Ten,
    Nine,
    Eight,
    Seven,
    Six,
    Five,
    Four,
    Three,
    Two,
    Joker,
)

fun Card.strength(): Int {
    val index = CARDS_BY_STRENGTH.indexOf(this)
    val size = CARDS_BY_STRENGTH.size
    return size - index
}

fun Char.toCard(): Card {
    return when (this) {
        'A' -> Ace
        'K' -> King
        'Q' -> Queen
        'J' -> Jack
        'T' -> Ten
        '9' -> Nine
        '8' -> Eight
        '7' -> Seven
        '6' -> Six
        '5' -> Five
        '4' -> Four
        '3' -> Three
        '2' -> Two
        else -> throw IllegalArgumentException("$this is not a card")
    }
}
