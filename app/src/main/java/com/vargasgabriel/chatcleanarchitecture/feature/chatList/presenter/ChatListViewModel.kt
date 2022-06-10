package com.vargasgabriel.chatcleanarchitecture.feature.chatList.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vargasgabriel.chatcleanarchitecture.data.ChatRepositoryImpl
import com.vargasgabriel.chatcleanarchitecture.data.source.ChatDataSource
import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.ChatListUseCase
import com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain.ChatListUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val chatListUseCase: ChatListUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ChatListViewState?> = MutableStateFlow(null)
    val uiState: Flow<ChatListViewState?> get() = _uiState

    init {
        viewModelScope.launch {
            _uiState.emit(ChatListViewState.Loading)
            _uiState.emit(ChatListViewState.Content(
                chats = chatListUseCase.lastChats().map { chat ->
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