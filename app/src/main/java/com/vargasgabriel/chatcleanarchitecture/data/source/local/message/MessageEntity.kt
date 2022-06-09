package com.vargasgabriel.chatcleanarchitecture.data.source.local.message

import java.time.OffsetDateTime

data class MessageEntity(
    var messageId: Int? = null,
    var messageUuid: String? = null,
    val chatId: Int,
    val textContent: String,
    val createdAt: OffsetDateTime,
    val sentAt: OffsetDateTime? = null,
)

