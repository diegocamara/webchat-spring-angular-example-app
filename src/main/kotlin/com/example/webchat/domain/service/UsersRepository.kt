package com.example.webchat.domain.service

interface UsersRepository {
    fun containsUsername(username: String): Boolean
    fun addUser(id: String, username: String)
    fun retrieveUsers(): MutableCollection<String>?
}