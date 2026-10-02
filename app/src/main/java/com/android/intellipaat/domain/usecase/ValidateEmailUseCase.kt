package com.android.intellipaat.domain.usecase

import com.android.intellipaat.domain.model.ValidationResult

class ValidateEmailUseCase {

    private val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")

    operator fun invoke(emailOrUsername: String): ValidationResult {
        val trimmedInput = emailOrUsername.trim()
        if (trimmedInput.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Username or Email cannot be empty"
            )
        }
        if (trimmedInput.contains("@") && !emailRegex.matches(trimmedInput)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please enter a valid email address"
            )
        }
        if (!trimmedInput.contains("@") && trimmedInput.length < 3) {
            return ValidationResult(
                successful = false,
                errorMessage = "Username must be at least 3 characters"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
