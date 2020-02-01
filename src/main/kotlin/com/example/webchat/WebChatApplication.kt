package com.example.webchat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class WebChatApplication

fun main(args: Array<String>) {
    runApplication<WebChatApplication>(*args)
}
