rootProject.name = "securityscanner"
include(
    "core",
    "infrastructure:aws:lambda",
    "infrastructure:aws:cdk"
)

pluginManagement {
    plugins {
        kotlin("jvm") version "1.6.10"
    }
}