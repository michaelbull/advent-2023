package com.github.michaelbull.advent2023.day20

typealias ModuleConfiguration = Map<String, Module>

fun Sequence<String>.toModuleConfiguration(): ModuleConfiguration {
    return map(String::toModule).associateBy(Module::name)
}
