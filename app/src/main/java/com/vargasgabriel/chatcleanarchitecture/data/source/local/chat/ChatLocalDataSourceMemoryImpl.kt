package com.vargasgabriel.chatcleanarchitecture.data.source.local.chat

import com.vargasgabriel.chatcleanarchitecture.data.source.ChatDataSource
import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.Chat
import javax.inject.Inject

class ChatLocalDataSourceMemoryImpl @Inject constructor() : ChatDataSource.Local {

    private var key = 0
    private val chats: MutableMap<Int, ChatEntity> = mutableMapOf()

    private fun autoIncrementKey(): Int {
        key += 1
        return key
    }

    override suspend fun lastChats(number: Int): List<Chat> {
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