package com.vargasgabriel.chatcleanarchitecture.data.source.local.chat

import java.time.OffsetDateTime

data class ChatEntity(
    val chatId: Int? = null,
    val chatUuid: String? = null,
    val name: String,
    val img: String, // URL
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
)
