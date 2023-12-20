package com.github.michaelbull.advent2023.day20

import com.github.michaelbull.advent2023.math.leastCommonMultiple

fun ModuleConfiguration.toNetwork(): Network {
    return Network(this)
}

class Network(configuration: ModuleConfiguration) {

    private val modules = configuration.toMutableMap()

    private val button = Pulse(
        strength = LowPulse,
        input = "button",
        output = "broadcaster",
    )

    fun pressButton(): Sequence<Pulse> = sequence {
        val pulses = ArrayDeque<Pulse>()

        pulses += button

        while (pulses.isNotEmpty()) {
            val pulse = pulses.removeFirst()
            yield(pulse)

            val module = modules[pulse.output] ?: continue
            pulses += module.receive(pulse)
        }
    }

    fun minButtonPresses(output: String, strength: PulseStrength): Long {
        val finalMachine = inputsOf(output).first()
        val finalInputs = inputsOf(finalMachine.name)

        val buttonPressesByInput = finalInputs
            .map(Module::name)
            .associateWith { 0L }
            .toMutableMap()

        var buttonPresses = 0L

        while (buttonPressesByInput.values.any { it == 0L }) {
            val pulses = pressButton().also {
                buttonPresses++
            }

            val deliveredToFinalMachine = pulses.filter { it.output == finalMachine.name }

            for (delivered in deliveredToFinalMachine) {
                val inputButtonPresses = buttonPressesByInput[delivered.input]

                if (delivered.strength != strength && inputButtonPresses == 0L) {
                    buttonPressesByInput[delivered.input] = buttonPresses
                }
            }
        }

        return buttonPressesByInput.values.leastCommonMultiple()
    }

    private fun Module.receive(pulse: Pulse): List<Pulse> {
        return when (this) {
            is Broadcast -> receive(pulse)
            is FlipFlop -> receive(pulse)
            is Conjunction -> receive(pulse)
        }
    }

    private fun Broadcast.receive(pulse: Pulse): List<Pulse> {
        return relay(pulse.strength)
    }

    private fun FlipFlop.receive(pulse: Pulse): List<Pulse> {
        return when (pulse.strength) {
            is HighPulse -> emptyList()

            is LowPulse -> {
                modules[name] = when (this) {
                    is FlipFlop.Off -> FlipFlop.On(name, destinations)
                    is FlipFlop.On -> FlipFlop.Off(name, destinations)
                }

                when (this) {
                    is FlipFlop.Off -> relay(HighPulse)
                    is FlipFlop.On -> relay(LowPulse)
                }
            }
        }
    }

    private fun Conjunction.receive(pulse: Pulse): List<Pulse> {
        val newHistory = history() + Pair(pulse.input, pulse.strength)

        modules[name] = copy(history = newHistory)

        val allHigh = newHistory.values.all { it is HighPulse }

        return if (allHigh) {
            relay(LowPulse)
        } else {
            relay(HighPulse)
        }
    }

    private fun Conjunction.history(): Map<String, PulseStrength> {
        return history.ifEmpty {
            initialHistory()
        }
    }

    private fun Conjunction.initialHistory(): Map<String, PulseStrength> {
        return inputsOf(name)
            .map(Module::name)
            .associateWith { LowPulse }
    }

    private fun Module.relay(strength: PulseStrength): List<Pulse> {
        return destinations.map { destination ->
            Pulse(strength, name, destination)
        }
    }

    private fun inputsOf(moduleName: String): List<Module> {
        return modules.values.filter { moduleName in it.destinations }
    }
}
