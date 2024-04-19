package com.example.myproject.domain.repository

import com.example.myproject.domain.model.ModelChat
import com.example.myproject.domain.model.ModelMessage
import com.example.myproject.domain.model.User
import com.example.myproject.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRespository {
    fun getAllContacts(deviceContacts : List<String>) : Flow<Resource<List<User>>>

    fun getAllChats(userId : String) : Flow<Resource<List<ModelChat>>>

    fun getAllMessagesOfChat(chatId : String) : Flow<Resource<List<ModelMessage>>>
    fun sendMessage(chatId: String, messageModel: ModelMessage): Flow<Resource<Boolean>>


}