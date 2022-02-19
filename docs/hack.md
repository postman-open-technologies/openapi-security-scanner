# Local development

Start by cloning this repository. Run:

```
git clone https://github.com/postman-open-technologies/openapi-security-scanner.git
```

Enter the newly cloned directory. Run:

```
cd openapi-security-scanner
```

Some local development dependencies are installed via npm. Run:

```
npm install
```

## Root project structure

```
.
├── core
└── infrastructure
    └── aws
        ├── cdk
        └── lambda
```

The `core` project contains the main program logic. There are adapters for infrastructure deployments. For AWS, there's
a `lambda` directory to hold a function that executes the core logic. The `cdk` project generates a CloudFormation
template using the Lambda function.

## AWS local development

Installing npm dependencies included a local install of the
[AWS Cloud Development Kit](https://aws.amazon.com/cdk/). This allows using a programming language, such as Kotlin, for
assembling AWS CloudFormation templates to be used for deployment.

To run the Lambda function locally through an API, the SAM CLI will be needed. Please see
[Installing the SAM CLI](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install.html)
for more info.

Once all the dependencies are installed, the AWS Lambda can be exposed via an API locally by running:

```
./gradlew awsStartApi
```