package com.postman.securityscanner.core

import io.kotest.core.spec.style.ExpectSpec


class ScannerTest : ExpectSpec({
    context("Scanner") {
        expect("scan to return alerts for a given seed Url") {
            //TODO("Installing ZAP reports add-on locally is flaky, can't do an integration test because of that")
        }
    }
})
