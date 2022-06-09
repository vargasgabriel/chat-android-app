package com.vargasgabriel.chatcleanarchitecture.data.source.remote.chat

import com.vargasgabriel.chatcleanarchitecture.data.source.ChatDataSource
import com.vargasgabriel.chatcleanarchitecture.data.source.local.chat.ChatEntity
import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.Chat

class ChatRemoteDataSourceMemoryImpl : ChatDataSource.Remote {

    private val chats: MutableMap<String, ChatEntity> = mutableMapOf()

    override suspend  fun lastChats(number: Int): List<Chat> {
        return chats.values
            .sortedBy { chat -> chat.chatId }
            .reversed()
            .takeLast(number)
            .map { entity ->
                Chat(
                    chatId = entity.chatId,
                    chatUuid = entity.chatUuid,
                    name = entity.name,
                    img = entity.img,
                    createdAt = entity.createdAt,
                    updatedAt = entity.updatedAt,
                )
            }
    }
}