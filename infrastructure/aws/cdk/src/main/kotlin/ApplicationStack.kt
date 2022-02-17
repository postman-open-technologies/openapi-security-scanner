import software.amazon.awscdk.Stack
import software.amazon.awscdk.StackProps
import software.amazon.awscdk.services.apigateway.*
import software.amazon.awscdk.services.lambda.Code
import software.amazon.awscdk.services.lambda.Runtime
import software.constructs.Construct
import software.amazon.awscdk.services.lambda.Function as LambdaFunction


class ApplicationStack(scope: Construct, id: String, props: StackProps? = null) : Stack(scope, id, props) {
    init {
        val stackSuffix = (node.tryGetContext("stackSuffix") as String?) ?: "main"
        val apiSuffix = (node.tryGetContext("apiSuffix") as String?) ?: stackSuffix
        val stageName = (node.tryGetContext("stageName") as String?) ?: if (stackSuffix == "main") "prod" else "dev"
        val function = LambdaFunction.Builder.create(this, "security-scanner-function-$apiSuffix-$stageName")
            .runtime(Runtime.JAVA_11)
            .handler("com.postman.securityscanner.infrastructure.aws.lambda.Handler")
            .code(Code.fromAsset("../lambda/build/distributions/aws-lambda-1.0-SNAPSHOT.zip"))
            .build()

        val api = RestApi.Builder.create(this, "security-scanner-api-$apiSuffix")
            .restApiName("security-scanner-api")
            .deploy(false)
            .build()

        val integration = LambdaIntegration.Builder.create(function).build()

        api.root.addMethod("ANY", integration)
        api.root.addResource("scan").addMethod("ANY", integration)
        api.root.addProxy(
            ProxyResourceOptions.builder()
                .anyMethod(true)
                .defaultIntegration(integration)
                .build()
        )

        val deployment = Deployment.Builder.create(this, "security-scanner-deployment-$apiSuffix-$stageName")
            .api(api)
            .build()

        val stage =
            Stage.Builder.create(this, "security-scanner-stage-$apiSuffix-$stageName")
                .stageName(stageName)
                .deployment(deployment)
                .build()

        api.deploymentStage = stage
    }
}