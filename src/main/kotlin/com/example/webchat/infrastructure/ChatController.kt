package com.example.webchat.infrastructure

import com.example.webchat.domain.service.UsersRepository
import com.example.webchat.infrastructure.model.Message
import com.example.webchat.infrastructure.model.MessageType
import org.springframework.messaging.handler.annotation.*
import org.springframework.stereotype.Controller
import java.time.LocalDateTime


@Controller
class ChatController(private val usersRepository: UsersRepository) {

    @MessageMapping("/chat/messages")
    @SendTo("/topic/messages")
    fun messageHandler(@Payload message: Message): Message {
        return message.copy(date = LocalDateTime.now(), type = MessageType.TEXT)
    }

    @MessageMapping("/chat/user/{username}")
    @SendTo("/topic/messages/{username}", "/topic/messages")
    fun newChatUser(@Header("simpSessionId") sessionId: String, @DestinationVariable username: String): Message {
        return if (usersRepository.containsUsername(username)) {
            Message(sender = username, error = "username already exists", type = MessageType.ERROR)
        } else {
            usersRepository.addUser(sessionId, username)
            Message(sender = username, chatUsers = usersRepository.retrieveUsers(), type = MessageType.NEW_USER)
        }
    }

}