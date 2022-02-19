package com.postman.securityscanner.core

import org.zaproxy.zap.ZAP

class Scanner {
    companion object {
        fun scan() {
            ZAP.main(
                arrayOf(
                    "-daemon",
                    "-host",
                    "0.0.0.0",
                    "-port",
                    "8080",
                    "-config",
                    "api.addrs.addr.name=.*",
                    "-config",
                    "api.addrs.addr.regex=true",
                    "-config",
                    "api.disablekey=true"
                )
            )

/*
            val api = ClientApi("localhost", 8080)
            val resp = api.spider.scan("https://www.google.com", null, null, null, null)
            val scanId = (resp as ApiResponseElement).value
            var progress = 0
            do {
                Thread.sleep(1000)
                progress = Integer.parseInt((api.spider.status(scanId) as ApiResponseElement).value)
            } while (progress < 100)
 */
        }
    }
}