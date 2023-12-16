package com.github.michaelbull.advent2023.day15

fun List<String>.hash(): Int {
    return sumOf(String::hash)
}

fun String.hash(): Int {
    return fold(0) { acc, char ->
        ((acc + char.code) * 17) % 256
    }
}
