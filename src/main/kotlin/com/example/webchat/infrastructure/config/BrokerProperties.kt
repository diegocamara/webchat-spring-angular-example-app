package com.example.webchat.infrastructure.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "broker")
data class BrokerProperties(
        val protocol: String,
        val host: String,
        val port: Long,
        val username: String,
        val password: String)