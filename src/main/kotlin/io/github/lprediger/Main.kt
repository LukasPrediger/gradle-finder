package io.github.lprediger

import java.nio.file.Path
import java.util.logging.ConsoleHandler
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.io.path.Path
import kotlin.io.path.absolute
import kotlin.io.path.absolutePathString
import kotlin.io.path.exists

val logger: Logger = Logger.getLogger("GradleFinder")

fun main(args: Array<String>) {
    logger.addHandler(ConsoleHandler())
    logger.level = if (args.contains("--info")) Level.FINE else Level.INFO
    logger.handlers.forEach { it.level = logger.level }

    val gradlew = findGradlew()
    val buildGradle = findBuildGradle()

    if (gradlew == null) {
        println("Couldn't find any gradle wrapper")
        return
    }

    if (buildGradle == null) {
        println("Couldn't find any build.gradle or build.gradle.kts")
        return
    }

    ProcessBuilder(
        gradlew.absolutePathString(),
        "-p",
        buildGradle.parent.absolutePathString(),
        *args
    ).inheritIO().start().waitFor()
}

private fun findBuildGradle() = (Path(".").searchUpwards("build.gradle")
    ?: Path("").searchUpwards("build.gradle.kts"))

private fun findGradlew(): Path? {
    val fileName = if (System.getProperty("os.name").lowercase().startsWith("windows")) "gradlew.bat" else "gradlew"
    return Path(".").searchUpwards(fileName)
}

fun Path.searchUpwards(filename: String): Path? {
    absolute().apply {
        logger.fine("Searching for $filename in ${absolutePathString()}")
        return when {
            resolve(filename).exists() -> resolve(filename).absolute()
            parent != null -> parent.searchUpwards(filename)
            else -> null
        }
    }
}