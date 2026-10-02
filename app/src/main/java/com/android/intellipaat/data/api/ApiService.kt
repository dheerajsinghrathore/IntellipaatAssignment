package com.android.intellipaat.data.api

import com.android.intellipaat.data.model.CourseDto
import com.android.intellipaat.data.model.LoginRequestDto
import com.android.intellipaat.data.model.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("user/login")
    suspend fun login(@Body request: LoginRequestDto): Response<LoginResponseDto>

    @GET("c/ce89-e12f-4d9b-8305")
    suspend fun getCourses(): List<CourseDto>
}
