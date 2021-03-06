package com.vargasgabriel.chatcleanarchitecture.feature.chatList.presenter

data class ChatViewState(
    val name: String,
    val img: String, // URL
//    val lastMessage: String,
) {
    override fun toString(): String {
        return "ChatViewState(name='$name', img='$img')"
    }
}

sealed class ChatListViewState {
    object Loading : ChatListViewState()
    object Error : ChatListViewState()
    data class Content(val chats: List<ChatViewState>) : ChatListViewState()
}