package com.android.intellipaat.domain.model

data class Course(
    val id: Int,
    val title: String,
    val instructor: String,
    val progress: Int,
    val lessons: Int,
    val completedLessonIds: Set<Int> = (1..lessons)
        .take((progress * lessons + 50) / 100)
        .toSet()
) {
    fun completeLesson(lessonId: Int): Course {
        if (lessonId !in 1..lessons || lessonId in completedLessonIds) return this

        val updatedCompletedLessons = completedLessonIds + lessonId
        return copy(
            completedLessonIds = updatedCompletedLessons,
            progress = updatedCompletedLessons.size * 100 / lessons
        )
    }
}
