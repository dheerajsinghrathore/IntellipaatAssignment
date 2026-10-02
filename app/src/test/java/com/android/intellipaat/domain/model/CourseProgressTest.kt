package com.android.intellipaat.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class CourseProgressTest {

    @Test
    fun completingLessonUpdatesStatusAndProgress() {
        val course = Course(
            id = 1,
            title = "Kotlin Basics",
            instructor = "Alex",
            progress = 0,
            lessons = 4
        )

        val updated = course.completeLesson(2)

        assertEquals(setOf(2), updated.completedLessonIds)
        assertEquals(25, updated.progress)
    }

    @Test
    fun completingAlreadyCompletedLessonDoesNotIncreaseProgressAgain() {
        val course = Course(
            id = 1,
            title = "Kotlin Basics",
            instructor = "Alex",
            progress = 25,
            lessons = 4
        )

        val updated = course.completeLesson(1)

        assertEquals(course.completedLessonIds, updated.completedLessonIds)
        assertEquals(course.progress, updated.progress)
    }
}
