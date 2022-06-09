package com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain

import com.vargasgabriel.chatcleanarchitecture.data.ChatRepository

class ChatListUseCaseImpl(
    private val chatRepository: ChatRepository
) : ChatListUseCase {

    override suspend fun lastChats() : List<Chat> {
        return try {
            chatRepository.lastChats(10)
        } catch (e: Exception) {
            listOf()
        }
    }
}

interface ChatListUseCase {
    suspend fun lastChats() : List<Chat>
}