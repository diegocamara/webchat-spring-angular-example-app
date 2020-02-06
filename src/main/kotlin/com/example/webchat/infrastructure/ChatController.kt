package com.example.webchat.infrastructure

import com.example.webchat.domain.service.UsersRepository
import com.example.webchat.infrastructure.model.Message
import com.example.webchat.infrastructure.model.MessageType
import org.springframework.messaging.handler.annotation.*
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Controller
import java.time.LocalDateTime


@Controller
class ChatController(private val usersRepository: UsersRepository,
                     private val simpMessageSendingOperations: SimpMessageSendingOperations) {

    @MessageMapping("/chat/messages")
    @SendTo("/chat/messages")
    fun messageHandler(@Payload message: Message): Message {
        return message.copy(date = LocalDateTime.now(), type = MessageType.TEXT)
    }

    @MessageMapping("/chat/user/{username}")
    @SendTo("/chat/messages")
    fun newChatUser(@Header("simpSessionId") sessionId: String, @DestinationVariable username: String): Message {

        val message = if (usersRepository.containsUsername(username)) {
            Message(sender = username, error = "username already exists", type = MessageType.ERROR)
        } else {
            usersRepository.addUser(sessionId, username)
            Message(sender = username, chatUsers = usersRepository.retrieveUsers(), type = MessageType.NEW_USER)
        }

        simpMessageSendingOperations.convertAndSend("/chat/messages/$username", message)

        return message
    }

}