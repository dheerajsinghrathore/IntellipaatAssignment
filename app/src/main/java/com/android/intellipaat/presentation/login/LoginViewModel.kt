package com.android.intellipaat.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.intellipaat.data.repository.AuthRepositoryImpl
import com.android.intellipaat.domain.usecase.LoginUseCase
import com.android.intellipaat.domain.usecase.ValidateEmailUseCase
import com.android.intellipaat.domain.usecase.ValidatePasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase = ValidateEmailUseCase(),
    private val validatePasswordUseCase: ValidatePasswordUseCase = ValidatePasswordUseCase(),
    private val loginUseCase: LoginUseCase = LoginUseCase(AuthRepositoryImpl())
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.EmailChanged -> {
                _uiState.update {
                    it.copy(
                        email = event.email,
                        emailError = null,
                        generalError = null
                    )
                }
            }
            is LoginUiEvent.PasswordChanged -> {
                _uiState.update {
                    it.copy(
                        password = event.password,
                        passwordError = null,
                        generalError = null
                    )
                }
            }
            is LoginUiEvent.Submit -> {
                submitData()
            }
            is LoginUiEvent.ClearError -> {
                _uiState.update { it.copy(generalError = null) }
            }
        }
    }

    private fun submitData() {
        val currentState = _uiState.value
        val emailResult = validateEmailUseCase(currentState.email)
        val passwordResult = validatePasswordUseCase(currentState.password)

        val hasError = !emailResult.successful || !passwordResult.successful

        if (hasError) {
            _uiState.update {
                it.copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, generalError = null) }

            val result = loginUseCase(currentState.email, currentState.password)

            result.onSuccess { user ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        loggedInUserName = user.name
                    )
                }
            }.onFailure { exception ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        generalError = exception.localizedMessage ?: "Login failed. Please try again."
                    )
                }
            }
        }
    }
}
