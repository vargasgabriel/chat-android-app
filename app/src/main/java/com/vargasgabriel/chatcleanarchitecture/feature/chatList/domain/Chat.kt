package com.vargasgabriel.chatcleanarchitecture.feature.chatList.domain

import java.time.OffsetDateTime

data class Chat(
    val chatId: Int? = null,
    val chatUuid: String? = null,
    val name: String,
    val img: String, // URL
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
)
