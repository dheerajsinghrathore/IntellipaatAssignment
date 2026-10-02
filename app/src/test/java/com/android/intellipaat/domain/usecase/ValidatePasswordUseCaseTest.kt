package com.android.intellipaat.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ValidatePasswordUseCaseTest {

    private lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @Before
    fun setUp() {
        validatePasswordUseCase = ValidatePasswordUseCase()
    }

    @Test
    fun `empty password returns error`() {
        val result = validatePasswordUseCase("")
        assertFalse(result.successful)
        assertEquals("Password cannot be empty", result.errorMessage)
    }

    @Test
    fun `short password returns error`() {
        val result = validatePasswordUseCase("12345")
        assertFalse(result.successful)
        assertEquals("Password must be at least 6 characters", result.errorMessage)
    }

    @Test
    fun `valid password returns success`() {
        val result = validatePasswordUseCase("password123")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }
}
