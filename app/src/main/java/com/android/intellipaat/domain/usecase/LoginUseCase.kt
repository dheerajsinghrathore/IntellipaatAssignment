package com.android.intellipaat.domain.usecase

import com.android.intellipaat.domain.model.User
import com.android.intellipaat.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return repository.login(email.trim(), password)
    }
}
