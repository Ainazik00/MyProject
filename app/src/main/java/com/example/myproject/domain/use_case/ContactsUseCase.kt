package com.example.myproject.domain.use_case

import com.example.myproject.domain.model.User
import com.example.myproject.domain.repository.UserRespository
import com.example.myproject.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactsUseCase @Inject constructor(
    private val userRespository: UserRespository
) {
    fun getAllWhatsAppContacts(deviceContacts: List<String>): Flow<Resource<List<User>>> {
        return userRespository.getAllContacts(deviceContacts)
    }
}