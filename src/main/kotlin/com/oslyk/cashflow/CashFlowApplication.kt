package com.oslyk.cashflow

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CashFlowApplication

fun main(args: Array<String>) {
    println("Running CashFlow Application")
    runApplication<CashFlowApplication>(*args)
}
