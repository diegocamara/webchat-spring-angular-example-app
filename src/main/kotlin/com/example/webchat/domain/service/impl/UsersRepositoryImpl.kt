package com.example.webchat.domain.service.impl

import com.example.webchat.domain.service.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersRepositoryImpl(private val users: MutableMap<String, String> = mutableMapOf()) : UsersRepository {

    override fun containsUsername(username: String): Boolean {
        return users.containsValue(username)
    }

    override fun addUser(id: String, username: String) {
        users[id] = username
    }

    override fun retrieveUsers(): MutableCollection<String>? {
        return users.values
    }


}