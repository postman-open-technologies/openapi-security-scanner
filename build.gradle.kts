val awsCdkSynth by tasks.register<Exec>("awsCdkSynth") {
    workingDir(file("infrastructure/aws/cdk"))
    commandLine(
        "../../../node_modules/.bin/cdk",
        "synth"
    )
}

task<Exec>("awsStartApi") {
    dependsOn(awsCdkSynth)
    workingDir(file("infrastructure/aws/cdk"))
    commandLine(
        "sam",
        "local",
        "start-api",
        "--template",
        "./cdk.out/postman-security-scanner.template.json"
    )
}