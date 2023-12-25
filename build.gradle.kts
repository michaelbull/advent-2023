group = "com.michael-bull.advent"
version = "1.0-SNAPSHOT"

plugins {
    application
    kotlin("jvm") version "1.9.21"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jgrapht:jgrapht-core:1.5.2")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("com.github.michaelbull.advent2023.MainKt")
}

tasks.test {
    jvmArgs = listOf("-Xss2m")
}

tasks.withType(JavaExec::class.java) {
    jvmArgs = listOf("-Xss2m")
}
