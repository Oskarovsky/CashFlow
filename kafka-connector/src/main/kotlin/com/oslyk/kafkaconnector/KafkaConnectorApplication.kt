package com.oslyk.kafkaconnector

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaConnectorApplication

fun main(args: Array<String>) {
    runApplication<KafkaConnectorApplication>(*args)
}
