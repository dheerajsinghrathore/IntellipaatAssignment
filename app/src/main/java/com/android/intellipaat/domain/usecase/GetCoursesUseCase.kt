package com.android.intellipaat.domain.usecase

import com.android.intellipaat.domain.model.Course
import com.android.intellipaat.domain.repository.CourseRepository

class GetCoursesUseCase(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(): Result<List<Course>> {
        return repository.getCourses()
    }
}
