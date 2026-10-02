package com.android.intellipaat.domain.repository

import com.android.intellipaat.domain.model.Course

interface CourseRepository {
    suspend fun getCourses(): Result<List<Course>>
}
