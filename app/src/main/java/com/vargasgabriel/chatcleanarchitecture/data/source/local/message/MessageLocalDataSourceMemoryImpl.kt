package com.vargasgabriel.chatcleanarchitecture.data.source.local.message

import com.vargasgabriel.chatcleanarchitecture.data.source.MessageDataSource
import com.vargasgabriel.chatcleanarchitecture.feature.chat.domain.Message
import javax.inject.Inject

class MessageLocalDataSourceMemoryImpl @Inject constructor() : MessageDataSource.Local {

    private var key = 0
    private val messages: MutableMap<Int, MessageEntity> = mutableMapOf()

    private fun autoIncrementKey(): Int {
        key += 1
        return key
    }

    override suspend fun lastMessages(number: Int): List<Message> {
        return messages.values
            .sortedBy { message -> message.messageId }
            .takeLast(number)
            .reversed()
            .map { entity ->
                Message(
                    messageId = entity.messageId,
                    messageUuid = entity.messageUuid,
                    textContent = entity.textContent,
                    createdAt = entity.createdAt,
                    sentAt = entity.sentAt
                )
            }
    }

    override suspend fun sendMessage(newMessage: MessageEntity): MessageEntity {
        val newSaveMessage = newMessage.copy(
            messageId = autoIncrementKey()
        )
        messages[newSaveMessage.messageId!!] = newSaveMessage
        return newSaveMessage
    }
}