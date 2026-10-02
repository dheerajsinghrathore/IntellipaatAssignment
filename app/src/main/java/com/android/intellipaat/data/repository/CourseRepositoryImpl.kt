package com.android.intellipaat.data.repository

import com.android.intellipaat.data.source.CourseRemoteDataSource
import com.android.intellipaat.data.source.CourseRemoteDataSourceImpl
import com.android.intellipaat.domain.model.Course
import com.android.intellipaat.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val remoteDataSource: CourseRemoteDataSource = CourseRemoteDataSourceImpl()
) : CourseRepository {

    override suspend fun getCourses(): Result<List<Course>> {
        return try {
            val dtos = remoteDataSource.getCourses()
            val courses = dtos.map { dto ->
                Course(
                    id = dto.id,
                    title = dto.title,
                    instructor = dto.instructor,
                    progress = dto.progress,
                    lessons = dto.lessons
                )
            }
            Result.success(courses)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
