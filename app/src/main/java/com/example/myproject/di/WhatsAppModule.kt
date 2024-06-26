package com.example.myproject.di

import android.content.Context
import androidx.room.Room
import com.example.myproject.data.database.UserDao
import com.example.myproject.data.database.UserDataBase
import com.example.myproject.data.repository.AuthRepositoryImpl
import com.example.myproject.data.repository.UserRespositoryImpl
import com.example.myproject.domain.repository.AuthRepository
import com.example.myproject.domain.repository.UserRespository
import com.example.myproject.domain.use_case.AuthenticationUseCase
import com.example.myproject.domain.use_case.ContactsUseCase
import com.example.myproject.util.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WhatsAppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDataBase {
        return Room.databaseBuilder(
            context,
            UserDataBase::class.java,
            Constants.appRoomDatabaseName
        )

            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: UserDataBase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore,
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth, firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideAuthenticationUseCase(authRepository: AuthRepository): AuthenticationUseCase {
        return AuthenticationUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        firestore: FirebaseFirestore,
        userDao: UserDao,
    ): UserRespository {
        return UserRespositoryImpl(firestore, userDao)
    }

    @Provides
    @Singleton
    fun provideContactsUseCase(repository: UserRespository): ContactsUseCase {
        return ContactsUseCase(repository)
    }

}