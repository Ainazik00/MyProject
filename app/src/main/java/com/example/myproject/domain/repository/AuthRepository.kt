package com.example.myproject.domain.repository


import com.example.myproject.domain.model.User
import com.example.myproject.presentation.MainActivity
import com.example.myproject.util.Resource
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun phoneNumberSignIn(phoneNumber : String,activity : MainActivity) : Flow<Resource<Boolean>>

    fun isUserAuthenticated() : Boolean

    fun getUserId() : String

    suspend fun signInWithAuthCredential(phoneAuthCredential: PhoneAuthCredential) : Resource<Boolean>

    fun createUserProfile(user: User) : Flow<Resource<Boolean>>
}