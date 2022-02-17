@file:Suppress("SpellCheckingInspection")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    //kotlin("jvm")
}

group = "com.postman.securityscanner.infrastructure.aws.lambda"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation(project(":core"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.register<Zip>("buildZip") {
    dependsOn("compileKotlin", "processResources")
    from("build/classes/kotlin/main")
    into("lib") {
        from(configurations.runtimeClasspath)
    }
}

tasks.named("classes") { finalizedBy("buildZip") }