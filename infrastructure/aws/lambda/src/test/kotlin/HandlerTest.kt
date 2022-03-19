import com.amazonaws.services.lambda.runtime.ClientContext
import com.amazonaws.services.lambda.runtime.CognitoIdentity
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger
import com.postman.securityscanner.infrastructure.aws.lambda.Handler
import java.io.IOException
import kotlin.test.Test

class HandlerTest {
    @Test
    fun testHandler() {
        val logger = object : LambdaLogger {
            override fun log(message: String?) {
                print(message)
            }

            override fun log(message: ByteArray?) {
                try {
                    System.out.write(message)
                } catch (e: IOException) {
                    // NOTE: When actually running on AWS Lambda, an IOException would never happen
                    e.printStackTrace()
                }
            }
        }

        val context = object : Context {
            override fun getAwsRequestId(): String {
                TODO("Not yet implemented")
            }

            override fun getLogGroupName(): String {
                TODO("Not yet implemented")
            }

            override fun getLogStreamName(): String {
                TODO("Not yet implemented")
            }

            override fun getFunctionName(): String {
                TODO("Not yet implemented")
            }

            override fun getFunctionVersion(): String {
                TODO("Not yet implemented")
            }

            override fun getInvokedFunctionArn(): String {
                TODO("Not yet implemented")
            }

            override fun getIdentity(): CognitoIdentity {
                TODO("Not yet implemented")
            }

            override fun getClientContext(): ClientContext {
                TODO("Not yet implemented")
            }

            override fun getRemainingTimeInMillis(): Int {
                TODO("Not yet implemented")
            }

            override fun getMemoryLimitInMB(): Int {
                TODO("Not yet implemented")
            }

            override fun getLogger(): LambdaLogger {
                return logger
            }
        }

        val handler = Handler()
        //handler.handleRequest(APIGatewayProxyRequestEvent(), context)
    }
}
