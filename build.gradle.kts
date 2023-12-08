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
    implementation("org.http4k:http4k-format-moshi")
    implementation("org.http4k:http4k-serverless-lambda")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.1")
    implementation("com.amazonaws:aws-lambda-java-events:3.11.2")
    implementation("io.arrow-kt:arrow-core:1.2.0-RC")
    implementation("com.commercetools.sdk:commercetools-http-client:8.10.0")
    implementation("com.commercetools.sdk:commercetools-sdk-java-api:8.10.0")
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