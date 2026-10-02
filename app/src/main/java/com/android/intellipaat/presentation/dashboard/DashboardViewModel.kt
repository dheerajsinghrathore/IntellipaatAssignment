package com.android.intellipaat.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.intellipaat.data.repository.CourseRepositoryImpl
import com.android.intellipaat.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getCoursesUseCase: GetCoursesUseCase = GetCoursesUseCase(CourseRepositoryImpl())
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun initUser(userName: String) {
        if (_uiState.value.userName != userName) {
            _uiState.update { it.copy(userName = userName) }
            fetchCourses()
        } else if (_uiState.value.courses.isEmpty()) {
            fetchCourses()
        }
    }

    fun fetchCourses() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            getCoursesUseCase()
                .onSuccess { courseList ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            courses = courseList
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = exception.localizedMessage
                                ?: "Failed to load course list."
                        )
                    }
                }
        }
    }

    fun markLessonCompleted(courseId: Int, lessonId: Int) {
        _uiState.update { state ->
            val updatedCourses = state.courses.map { course ->
                if (course.id == courseId) course.completeLesson(lessonId) else course
            }
            state.copy(courses = updatedCourses)
        }
    }
}
