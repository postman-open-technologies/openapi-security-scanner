val awsCdkSynth by tasks.register<Exec>("awsCdkSynth") {
    workingDir(file("infrastructure/aws/cdk"))
    commandLine(
        "../../../node_modules/.bin/cdk",
        "synth"
    )
    inputs.dir("core/src/main")

    inputs.file("infrastructure/aws/cdk/cdk.json")
    inputs.dir("infrastructure/aws/cdk/src/main")

    inputs.dir("infrastructure/aws/lambda/src/main")
    inputs.file("infrastructure/aws/lambda/Dockerfile")
    outputs.dir("infrastructure/aws/cdk/cdk.out")
}

task<Exec>("awsSamBuild") {
    dependsOn(awsCdkSynth)
    workingDir(file("infrastructure/aws/cdk"))
    commandLine(
        "sam",
        "build",
        "--template",
        "./cdk.out/postman-security-scanner.template.json"
    )
    inputs.dir("infrastructure/aws/cdk/cdk.out")
    outputs.dir("infrastructure/aws/cdk/.aws-sam")
}

task<Exec>("awsStartApi") {
    workingDir(file("infrastructure/aws/cdk"))
    commandLine(
        "sam",
        "local",
        "start-api",
        "--template",
        "./cdk.out/postman-security-scanner.template.json"
    )
}