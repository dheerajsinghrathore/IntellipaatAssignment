package com.android.intellipaat.data.repository

import com.android.intellipaat.data.source.AuthRemoteDataSource
import com.android.intellipaat.data.source.AuthRemoteDataSourceImpl
import com.android.intellipaat.domain.model.User
import com.android.intellipaat.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource = AuthRemoteDataSourceImpl()
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val user = remoteDataSource.login(email, password)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
