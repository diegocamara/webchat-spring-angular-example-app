package com.example.webchat.infrastructure.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Message(val type: MessageType? = null,
                   val sender: String? = null,
                   val text: String? = null,
                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") val date: LocalDateTime? = null,
                   val chatUsers: MutableCollection<String>? = null,
                   val error: String? = null)

enum class MessageType {
    NEW_USER, TEXT, USERLEAVE, ERROR;
}