package com.vargasgabriel.chatcleanarchitecture.di

import com.vargasgabriel.chatcleanarchitecture.data.ChatRepository
import com.vargasgabriel.chatcleanarchitecture.data.ChatRepositoryImpl
import com.vargasgabriel.chatcleanarchitecture.data.MessageRepository
import com.vargasgabriel.chatcleanarchitecture.data.MessageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModules {

    @Binds
    abstract fun bindChatRepository(
        repositoryImpl: ChatRepositoryImpl
    ): ChatRepository

    @Binds
    abstract fun bindMessageRepository(
        repositoryImpl: MessageRepositoryImpl
    ): MessageRepository
}