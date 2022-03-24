import software.amazon.awscdk.Duration
import software.amazon.awscdk.Stack
import software.amazon.awscdk.StackProps
import software.amazon.awscdk.services.apigateway.*
import software.amazon.awscdk.services.lambda.AssetImageCodeProps
import software.amazon.awscdk.services.lambda.DockerImageCode
import software.amazon.awscdk.services.lambda.DockerImageFunction
import software.constructs.Construct

class ApplicationStack(scope: Construct, id: String, props: StackProps? = null) :
    Stack(scope, id, props) {
    val handler = "com.postman.securityscanner.infrastructure.aws.lambda.Handler::handleRequest"
    val prefix = "security-scanner"

    init {
        val stackSuffix = (node.tryGetContext("stackSuffix") as String?) ?: "main"
        val apiSuffix = (node.tryGetContext("apiSuffix") as String?) ?: stackSuffix
        val stageName =
            (node.tryGetContext("stageName") as String?)
                ?: if (stackSuffix == "main") "prod" else "dev"

        val codeProps = AssetImageCodeProps.builder().cmd(listOf(handler)).build()
        val code = DockerImageCode.fromImageAsset("../lambda", codeProps)
        val function =
            DockerImageFunction.Builder.create(this, "$prefix-function-$apiSuffix-$stageName")
                .code(code)
                .functionName("scanner")
                .timeout(Duration.minutes(15))
                .memorySize(10240)
                .build()

        val api =
            RestApi.Builder.create(this, "$prefix-api-$apiSuffix")
                .restApiName("$prefix-api")
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

        val deployment =
            Deployment.Builder.create(this, "$prefix-deployment-$apiSuffix-$stageName")
                .api(api)
                .build()

        val stage =
            Stage.Builder.create(this, "$prefix-stage-$apiSuffix-$stageName")
                .stageName(stageName)
                .deployment(deployment)
                .build()

        api.deploymentStage = stage
    }
}
