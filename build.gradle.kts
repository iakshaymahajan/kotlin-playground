plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.http4k:http4k-bom:4.48.0.0"))
    implementation("org.http4k:http4k-format-moshi:4.44.0.0")
    implementation("org.http4k:http4k-serverless-lambda:4.40.2.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.1")
    implementation("com.amazonaws:aws-lambda-java-events:3.11.2")
    implementation("io.arrow-kt:arrow-core:1.2.0-RC")
    implementation("com.commercetools.sdk:commercetools-http-client:13.0.0")
    implementation("com.commercetools.sdk:commercetools-sdk-java-api:13.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.slf4j:slf4j-nop:2.0.7")
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("io.github.microutils:kotlin-logging:2.1.23")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}