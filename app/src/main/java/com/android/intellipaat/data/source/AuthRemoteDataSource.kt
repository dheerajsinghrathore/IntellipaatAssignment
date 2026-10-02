package com.android.intellipaat.data.source

import com.android.intellipaat.domain.model.User

interface AuthRemoteDataSource {
    suspend fun login(emailOrUsername: String, password: String): User
}
