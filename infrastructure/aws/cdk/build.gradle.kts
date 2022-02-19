@file:Suppress("SpellCheckingInspection")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "com.postman.securityscanner.infrastructure.aws.cdk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")

    implementation("software.amazon.awscdk:aws-cdk-lib:2.10.0")
    implementation("software.constructs:constructs:10.0.62")
    runtimeOnly(project(":infrastructure:aws:lambda"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("AppKt")
}