package com.android.intellipaat.presentation.dashboard

import com.android.intellipaat.domain.model.Course

data class DashboardUiState(
    val userName: String = "",
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
