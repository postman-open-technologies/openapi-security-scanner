val awsCdkSynth by tasks.register<Exec>("awsCdkSynth") {
    workingDir(file("cdk"))
    commandLine(
        "npx",
        "cdk",
        "synth"
    )
}

task<Exec>("awsSamBuild") {
  dependsOn(awsCdkSynth)
  workingDir(file("cdk"))
  commandLine(
    "sam",
    "build",
    "--template",
    "./cdk.out/postman-security-scanner.template.json"
  )
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