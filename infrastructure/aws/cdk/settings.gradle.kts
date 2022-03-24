rootProject.name = "cdk"
include(":infrastructure:aws:lambda")
project(":infrastructure:aws:lambda").projectDir = file("../lambda")

pluginManagement {
    plugins {
        kotlin("jvm") version "1.6.10"
    }
}
