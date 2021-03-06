package com.example.webchat.infrastructure.config

import com.example.webchat.infrastructure.properties.BrokerProperties
import org.apache.activemq.broker.BrokerService
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Configuration
class ActiveMQConfig(val brokerProperties: BrokerProperties) {

    lateinit var brokerService: BrokerService

    @PostConstruct
    fun startActiveMQ() {
        brokerService = BrokerService()
        brokerService.isUseJmx = false
        brokerService.isPersistent = false
        brokerService.addConnector("${brokerProperties.protocol}://${brokerProperties.host}:${brokerProperties.port}")
//        val authenticationUser = AuthenticationUser(brokerProperties.username, brokerProperties.password, "users")
//        val simpleAuthenticationPlugin = SimpleAuthenticationPlugin(listOf(authenticationUser))
//        brokerService.plugins = arrayOf(simpleAuthenticationPlugin)
        brokerService.start()
    }

    @PreDestroy
    fun destroy() {
        brokerService.stop()
    }

}