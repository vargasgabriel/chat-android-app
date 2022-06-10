package com.vargasgabriel.chatcleanarchitecture.di

import com.vargasgabriel.chatcleanarchitecture.data.source.ChatDataSource
import com.vargasgabriel.chatcleanarchitecture.data.source.MessageDataSource
import com.vargasgabriel.chatcleanarchitecture.data.source.local.chat.ChatLocalDataSourceMemoryImpl
import com.vargasgabriel.chatcleanarchitecture.data.source.local.message.MessageLocalDataSourceMemoryImpl
import com.vargasgabriel.chatcleanarchitecture.data.source.remote.chat.ChatRemoteDataSourceMemoryImpl
import com.vargasgabriel.chatcleanarchitecture.data.source.remote.message.MessageRemoteDataSourceMemoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModules {

    @Binds
    abstract fun bindChatLocalDataSource(
        datasourceImpl: ChatLocalDataSourceMemoryImpl
    ): ChatDataSource.Local

    @Binds
    abstract fun bindChatRemoteDataSource(
        datasourceImpl: ChatRemoteDataSourceMemoryImpl
    ): ChatDataSource.Remote

    @Binds
    abstract fun bindMessageLocalDataSource(
        datasourceImpl: MessageLocalDataSourceMemoryImpl
    ): MessageDataSource.Local

    @Binds
    abstract fun bindMessageRemoteDataSource(
        datasourceImpl: MessageRemoteDataSourceMemoryImpl
    ): MessageDataSource.Remote

}