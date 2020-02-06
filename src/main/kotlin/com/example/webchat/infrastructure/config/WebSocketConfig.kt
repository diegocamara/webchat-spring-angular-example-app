package com.example.webchat.infrastructure.config

import com.example.webchat.infrastructure.properties.BrokerProperties
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig(val brokerProperties: BrokerProperties) : WebSocketMessageBrokerConfigurer {


    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.setApplicationDestinationPrefixes("/app")
        registry.enableStompBrokerRelay("/chat")
                .setRelayHost(brokerProperties.host)
                .setRelayPort(brokerProperties.port)
//                .setSystemLogin(brokerProperties.username)
//                .setSystemPasscode(brokerProperties.password)
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/chat").setAllowedOrigins("http://localhost:4200")
    }
}
