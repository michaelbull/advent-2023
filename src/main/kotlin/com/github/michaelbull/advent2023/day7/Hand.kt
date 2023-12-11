package com.github.michaelbull.advent2023.day7

fun String.toHand(): Hand {
    val cards = map(Char::toCard)

    return Hand(
        type = cards.handType(),
        cards = cards,
    )
}

data class Hand(
    val type: HandType,
    val cards: List<Card>,
) {

    fun jokerRule(): Hand {
        val replacedCards = cards.map(::replaceJackWithJoker)

        return copy(
            type = replacedCards.jokerHandType(),
            cards = replacedCards,
        )
    }

    private fun replaceJackWithJoker(card: Card): Card {
        return if (card == Jack) Joker else card
    }
}

private fun List<Card>.handType(): HandType {
    return when {
        allEqual() -> FiveOfAKind
        fourOfAKind() -> FourOfAKind
        fullHouse() -> FullHouse
        threeOfAKind() -> ThreeOfAKind
        twoPair() -> TwoPair
        onePair() -> OnePair
        else -> HighCard
    }
}

private fun List<Card>.jokerHandType(): HandType {
    val jokers = count { it == Joker }

    return when {
        allEqual() -> FiveOfAKind

        fourOfAKind() -> when (jokers) {
            4 -> FiveOfAKind
            1 -> FiveOfAKind
            0 -> FourOfAKind
            else -> throw IllegalStateException()
        }

        fullHouse() -> when (jokers) {
            3 -> FiveOfAKind
            2 -> FiveOfAKind
            0 -> FullHouse
            else -> throw IllegalStateException()
        }

        threeOfAKind() -> when (jokers) {
            3 -> FourOfAKind
            1 -> FourOfAKind
            0 -> ThreeOfAKind
            else -> throw IllegalStateException()
        }

        twoPair() -> when (jokers) {
            2 -> FourOfAKind
            1 -> FullHouse
            0 -> TwoPair
            else -> throw IllegalStateException()
        }

        onePair() -> when (jokers) {
            2 -> ThreeOfAKind
            1 -> ThreeOfAKind
            0 -> OnePair
            else -> throw IllegalStateException()
        }

        else -> when (jokers) {
            1 -> OnePair
            0 -> HighCard
            else -> throw IllegalStateException()
        }
    }
}

private fun <T> Iterable<T>.fourOfAKind(): Boolean {
    val quadruple = findByCount(4)
    return quadruple != null
}

private fun <T> Iterable<T>.fullHouse(): Boolean {
    val triple = findByCount(3)

    return if (triple == null) {
        false
    } else {
        val remaining = filterNot { element -> element == triple }
        return remaining.allEqual()
    }
}

private fun <T> Iterable<T>.threeOfAKind(): Boolean {
    val triple = findByCount(3)

    return if (triple == null) {
        false
    } else {
        val remaining = filterNot { element -> element == triple }

        remaining.none { element ->
            val count = count { other -> other == element }
            count > 1
        }
    }
}

private fun <T> Iterable<T>.twoPair(): Boolean {
    val firstPair = findByCount(2)

    return if (firstPair == null) {
        false
    } else {
        val remaining = filterNot { element -> element == firstPair }
        val secondPair = remaining.findByCount(2)

        secondPair != null
    }
}

private fun <T> Iterable<T>.onePair(): Boolean {
    val firstPair = findByCount(2)

    return if (firstPair == null) {
        false
    } else {
        val remaining = filterNot { element -> element == firstPair }
        val secondPair = remaining.findByCount(2)

        secondPair == null
    }
}

private fun <T> Iterable<T>.allEqual(): Boolean {
    val first = first()
    return all { it == first }
}

private fun <T> Iterable<T>.findByCount(count: Int): T? {
    return find { element ->
        val elementCount = count { other -> element == other }
        elementCount == count
    }
}
