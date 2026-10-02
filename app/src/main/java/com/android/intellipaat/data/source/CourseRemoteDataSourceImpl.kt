package com.android.intellipaat.data.source

import com.android.intellipaat.data.api.ApiClient
import com.android.intellipaat.data.api.ApiService
import com.android.intellipaat.data.model.CourseDto

class CourseRemoteDataSourceImpl(
    private val apiService: ApiService = ApiClient.apiService
) : CourseRemoteDataSource {

    override suspend fun getCourses(): List<CourseDto> {
        return apiService.getCourses()
    }
}
