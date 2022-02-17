package com.postman.securityscanner.infrastructure.aws.lambda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.postman.securityscanner.core.Scanner

class Handler : RequestHandler<Map<String, String>, String> {
    private var gson: Gson = GsonBuilder().setPrettyPrinting().create()
    override fun handleRequest(event: Map<String, String>?, context: Context?): String {
        val logger = context!!.logger
        val response = "200 OK"
        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()))
        logger.log("CONTEXT: " + gson.toJson(context))
        logger.log("MESSAGE:" + Scanner.message + "\n")
        // process event
        logger.log("EVENT: " + gson.toJson(event))
        logger.log("EVENT TYPE: " + event!!::class)
        return response
    }
}