package com.github.michaelbull.advent2023.day20

sealed interface PulseStrength

data object HighPulse : PulseStrength
data object LowPulse : PulseStrength
