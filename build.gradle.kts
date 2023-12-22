plugins {
    kotlin("jvm") version "1.9.0"

    id("io.gitlab.arturbosch.detekt") version("1.23.4")
    id("org.jlleitschuh.gradle.ktlint") version ("11.6.1")

    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-jetty:4.48.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    testImplementation("org.http4k:http4k-client-jetty:4.48.0.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("Main")
}
