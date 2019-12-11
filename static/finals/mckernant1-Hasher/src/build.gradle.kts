import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.3.61"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.3.61"
    id("com.github.johnrengelman.shadow") version "5.1.0"
    // Apply the application plugin to add support for building a CLI application.
    application
}

val run by tasks.getting(JavaExec::class) {
    standardInput = System.`in`
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.14.0")
    implementation("info.picocli:picocli:4.1.1")
    annotationProcessor("info.picocli:picocli-codegen:4.1.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    // Define the main class for the application.
    mainClassName = "hasher.AppKt"
}

tasks.withType<ShadowJar>() {
    manifest {
        attributes["Main-Class"] = "AppKt"
    }
}
