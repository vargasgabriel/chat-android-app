package com.vargasgabriel.chatcleanarchitecture.data.source

import com.vargasgabriel.chatcleanarchitecture.data.source.local.message.MessageEntity
import com.vargasgabriel.chatcleanarchitecture.feature.chat.domain.Message

interface MessageDataSource {

    interface Local {
        suspend fun lastMessages(number: Int): List<Message>

        suspend fun sendMessage(newMessage: MessageEntity): MessageEntity
    }

    interface Remote {
//        suspend fun sendMessage(newMessage: MessageEntity): MessageEntity
    }
}