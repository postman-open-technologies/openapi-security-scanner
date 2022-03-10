package com.postman.securityscanner.infrastructure.aws.lambda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.postman.securityscanner.core.Scanner

class Handler : RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private var gson: Gson = GsonBuilder().setPrettyPrinting().create()
    override fun handleRequest(event: APIGatewayProxyRequestEvent?, context: Context?): APIGatewayProxyResponseEvent {
        val logger = context!!.logger
        logger.log("CONTEXT: " + gson.toJson(context))
        val seedUrl = event?.queryStringParameters?.get("url").orEmpty()
        val report = Scanner.scan(seedUrl)
        return APIGatewayProxyResponseEvent()
            .withStatusCode(200)
            .withHeaders(mapOf("Content-Type" to "application/json"))
            .withBody(report)
    }
}