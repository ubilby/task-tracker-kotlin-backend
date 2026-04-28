plugins {
    id("task-tracker.kotlin-conventions")
}

dependencies {
    testImplementation(libs.kotest.runner)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.property)
}

group = "io.github.ubilby"
version = "1.0-SNAPSHOT"
