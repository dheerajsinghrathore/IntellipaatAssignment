package com.android.intellipaat.data.source

import com.android.intellipaat.data.model.CourseDto

interface CourseRemoteDataSource {
    suspend fun getCourses(): List<CourseDto>
}
