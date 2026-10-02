package com.android.intellipaat.domain.usecase

import com.android.intellipaat.domain.model.Course
import com.android.intellipaat.domain.repository.CourseRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCoursesUseCaseTest {

    private lateinit var fakeRepository: FakeCourseRepository
    private lateinit var getCoursesUseCase: GetCoursesUseCase

    @Before
    fun setUp() {
        fakeRepository = FakeCourseRepository()
        getCoursesUseCase = GetCoursesUseCase(fakeRepository)
    }

    @Test
    fun `getCourses returns list from repository`() = runBlocking {
        val result = getCoursesUseCase()
        assertTrue(result.isSuccess)

        val courses = result.getOrNull()
        assertEquals(3, courses?.size)
        assertEquals("Python Programming", courses?.get(0)?.title)
        assertEquals("Generative AI", courses?.get(1)?.title)
        assertEquals("Full Stack Development", courses?.get(2)?.title)
    }
}

private class FakeCourseRepository : CourseRepository {
    override suspend fun getCourses(): Result<List<Course>> {
        return Result.success(
            listOf(
                Course(1, "Python Programming", "John Smith", 65, 20),
                Course(2, "Generative AI", "Sarah Williams", 40, 16),
                Course(3, "Full Stack Development", "David Brown", 25, 28)
            )
        )
    }
}
