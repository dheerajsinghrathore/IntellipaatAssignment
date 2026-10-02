package com.android.intellipaat.domain.repository

import com.android.intellipaat.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
}
