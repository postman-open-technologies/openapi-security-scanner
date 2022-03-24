package com.postman.securityscanner.core

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.zaproxy.clientapi.core.ApiResponse
import org.zaproxy.clientapi.core.ApiResponseElement
import org.zaproxy.clientapi.core.ClientApi
import org.zaproxy.clientapi.core.ClientApiException
import java.io.File
import java.io.IOException

object Scanner {
    private var activeConnection: Boolean = false
    private const val PORT = 8090
    private const val ADDRESS = "0.0.0.0"
    private val clientApi = ClientApi(ADDRESS, PORT, true)

    fun scan(zapPath: String, zapDir: String, seedUrl: String): String {
        if (!activeConnection) {
            runBlocking {
                launch {
                    exec(
                        "$zapPath " +
                                "-dir $zapDir " +
                                "-daemon " +
                                "-host 0.0.0.0 " +
                                "-port 8090 " +
                                "-config api.addrs.addr.name=.* " +
                                "-config api.addrs.addr.regex=true " +
                                "-config api.disablekey=true"
                    )
                }
            }
        }

        try {
            clientApi.waitForSuccessfulConnectionToZap(50, 2)
            activeConnection = true
        } catch (exception: ClientApiException) {
            throw exception
        }

        try {
            val apiResponse: ApiResponse = clientApi.spider.scan(seedUrl, null, null, null, null)
            var progress: Int

            // The scan now returns a scan id to support concurrent scanning
            var scanid = (apiResponse as ApiResponseElement).value

            // Poll the status until it completes
            while (true) {
                Thread.sleep(1000)
                progress =
                    (clientApi.spider.status(scanid) as ApiResponseElement).value.toInt()
                println("Spider progress : $progress%")
                if (progress >= 100) {
                    break
                }
            }
            println("Spider complete")
            val allUrls = clientApi.spider.allUrls()
            // Poll the number of records the passive scanner still has to scan until it completes

            while (true) {
                Thread.sleep(1000)
                progress =
                    (clientApi.pscan.recordsToScan() as ApiResponseElement).value.toInt()
                println("Passive Scan progress : $progress records left")
                if (progress < 1) {
                    break
                }
            }
            println("Passive Scan complete")

            val resp = clientApi.ascan.scan(seedUrl, "True", "False", null, null, null)

            // The scan now returns a scan id to support concurrent scanning
            scanid = (resp as ApiResponseElement).value

            // Poll the status until it completes
            while (true) {
                Thread.sleep(5000)
                progress =
                    (clientApi.ascan.status(scanid) as ApiResponseElement).value.toInt()
                println("Active Scan progress : $progress%")
                if (progress >= 100) {
                    break
                }
            }
            println("Active Scan complete")
            println("Alerts:")
            val report = clientApi.reports.generate(
                "Juice Box",
                "traditional-json",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
            println((report as ApiResponseElement).toString())
            return File(report.toString()).inputStream().readAllBytes().decodeToString()
        } catch (e: Exception) {
            println("Exception : " + e.message)
            e.printStackTrace()
        } finally {
        }
        return "Couldn't scan"
    }


    private fun exec(
        cmd: String,
        workingDir: File = File(".")
    ) {
        try {
            ProcessBuilder(*cmd.split("\\s".toRegex()).toTypedArray())
                .directory(workingDir)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}