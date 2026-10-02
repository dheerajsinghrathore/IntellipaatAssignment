package com.android.intellipaat.presentation.login

data class LoginUiState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val generalError: String? = null,
    val isLoggedIn: Boolean = false,
    val loggedInUserName: String = ""
)
