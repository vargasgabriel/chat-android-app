package com.vargasgabriel.chatcleanarchitecture.di

import com.vargasgabriel.chatcleanarchitecture.data.ChatRepository
import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.ChatListUseCase
import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.ChatListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModules {

    @Binds
    abstract fun bindsChatListUseCase(
        chatRepository: ChatListUseCaseImpl
    ): ChatListUseCase
}