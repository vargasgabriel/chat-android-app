package com.vargasgabriel.chatcleanarchitecture.data

import com.vargasgabriel.chatcleanarchitecture.data.source.MessageDataSource
import com.vargasgabriel.chatcleanarchitecture.data.source.local.message.MessageEntity
import com.vargasgabriel.chatcleanarchitecture.feature.chat.domain.Message
import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.Chat

class MessageRepositoryMemoryImpl(
    private val localDataSource: MessageDataSource.Local
) : MessageRepository {

    override suspend fun lastMessages(number: Int): List<Message> {
        return localDataSource.lastMessages(number)
    }

    override suspend fun sendMessage(chat: Chat, newMessage: Message): Message {
        // chat.chatId == null erro, mensagem ter que ter chat id

        val newMessageEntity = localDataSource.sendMessage(
            MessageEntity(
                chatId = chat.chatId!!,
                textContent = newMessage.textContent,
                createdAt = newMessage.createdAt,
            )
        )
        return Message(
            messageId = newMessageEntity.messageId,
            messageUuid = newMessageEntity.messageUuid,
            textContent = newMessageEntity.textContent,
            createdAt = newMessageEntity.createdAt,
            sentAt = newMessageEntity.sentAt
        )
    }
}