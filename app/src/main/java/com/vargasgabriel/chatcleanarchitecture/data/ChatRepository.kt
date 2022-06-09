package com.vargasgabriel.chatcleanarchitecture.data

import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.Chat

interface ChatRepository {

    suspend fun lastChats(number: Int) : List<Chat>

}