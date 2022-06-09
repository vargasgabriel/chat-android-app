package com.vargasgabriel.chatcleanarchitecture.data

import com.vargasgabriel.chatcleanarchitecture.feature.chat.domain.Message
import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.Chat

interface MessageRepository {

    suspend fun lastMessages(number: Int) : List<Message>

    suspend fun sendMessage(chat: Chat, newMessage: Message): Message
}