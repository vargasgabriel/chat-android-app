package com.vargasgabriel.chatcleanarchitecture.data.source

import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.Chat

interface ChatDataSource {

    interface Local {
        suspend fun lastChats(number: Int) : List<Chat>
    }

    interface Remote {
        suspend fun lastChats(number: Int) : List<Chat>
    }
}