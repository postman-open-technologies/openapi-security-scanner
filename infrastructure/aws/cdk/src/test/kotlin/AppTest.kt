import software.amazon.awscdk.App
import software.amazon.awscdk.assertions.Template
import kotlin.test.Test

class AppTest {

    @Test
    fun testStack() {
        val app = App()
        val stack = ApplicationStack(app, "test")

        val template = Template.fromStack(stack)

        template.hasResourceProperties("AWS::Lambda::Function", hashMapOf("Runtime" to "java11"))
    }
}
