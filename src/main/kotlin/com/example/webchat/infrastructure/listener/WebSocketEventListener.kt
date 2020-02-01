package com.example.webchat.infrastructure.listener

import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectedEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent

@Component
class WebSocketEventListener(val simpMessageSendingOperations: SimpMessageSendingOperations) {

    @EventListener
    fun onSessionConnectedEvent(sessionConnectedEvent: SessionConnectedEvent) {

    }

    @EventListener
    fun onSessionDisconnectEvent(sessionDisconnectEvent: SessionDisconnectEvent) {

    }

}