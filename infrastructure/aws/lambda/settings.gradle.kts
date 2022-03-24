rootProject.name = "lambda"
include(":core")
project(":core").projectDir = file("../../../core")
pluginManagement {
    plugins {
        kotlin("jvm") version "1.6.10"
    }
}
