import software.amazon.awscdk.App
import software.amazon.awscdk.StackProps

fun main() {
    val app = App()

    ApplicationStack(app, "postman-security-scanner", StackProps.builder().build())

    app.synth()
}