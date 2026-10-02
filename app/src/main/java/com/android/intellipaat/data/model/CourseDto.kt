package com.android.intellipaat.data.model

import com.google.gson.annotations.SerializedName

data class CourseDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("instructor") val instructor: String,
    @SerializedName("progress") val progress: Int,
    @SerializedName("lessons") val lessons: Int
)
