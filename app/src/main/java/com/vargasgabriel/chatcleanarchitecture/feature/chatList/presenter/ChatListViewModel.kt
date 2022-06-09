package com.vargasgabriel.chatcleanarchitecture.feature.chatList.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vargasgabriel.chatcleanarchitecture.data.ChatRepositoryMemoryImpl
import com.vargasgabriel.chatcleanarchitecture.data.source.ChatDataSource
import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.ChatListUseCase
import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.ChatListUseCaseImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ChatListViewModel(
    private val listChatUseCase: ChatListUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ChatListViewState?> = MutableStateFlow(null)
    val uiState: Flow<ChatListViewState?> get() = _uiState

    init {
        viewModelScope.launch {
            _uiState.emit(ChatListViewState.Loading)
            _uiState.emit(ChatListViewState.Content(
                chats = listChatUseCase.lastChats().map { chat ->
                    ChatViewState(
                        name = chat.name,
                        img = chat.img,
//                        lastMessage = chat.messages.last()
                    )
                }
            ))
        }
    }
}

class ChatListViewModelFactory(
    private val chatLocalDataSource: ChatDataSource.Local,
    private val chatRemoteDataSource: ChatDataSource.Remote,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatListViewModel::class.java)) {
            return ChatListViewModel(
                ChatListUseCaseImpl(
                    ChatRepositoryMemoryImpl(
                        chatLocalDataSource,
                        chatRemoteDataSource
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}