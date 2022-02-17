rootProject.name = "cdk"
include(":infrastructure:aws:lambda")
project(":infrastructure:aws:lambda").projectDir = file("../lambda")