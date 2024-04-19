package com.example.myproject.domain.use_case

import com.example.myproject.domain.model.User
import com.example.myproject.domain.repository.AuthRepository
import com.example.myproject.presentation.MainActivity
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    fun phoneNumberSignIn(phoneNumber: String, activity: MainActivity) =
        authRepository.phoneNumberSignIn(activity = activity, phoneNumber = phoneNumber)

    fun isUserAuthenticated() = authRepository.isUserAuthenticated()

    fun getUserId() = authRepository.getUserId()

    fun createProfile(user: User) = authRepository.createUserProfile(user)

}