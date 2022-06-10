package com.vargasgabriel.chatcleanarchitecture.data

import com.vargasgabriel.chatcleanarchitecture.data.source.ChatDataSource
import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.Chat
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val localDataSource: ChatDataSource.Local,
    private val remoteDataSource: ChatDataSource.Remote,
) : ChatRepository {
    override suspend  fun lastChats(number: Int): List<Chat> {
        // get local or remote
        return localDataSource.lastChats(number)
    }
}