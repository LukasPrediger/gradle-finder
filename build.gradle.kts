plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "io.github.lprediger"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass = "io.github.lprediger.MainKt"
}
