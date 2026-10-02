package com.android.intellipaat.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ValidateEmailUseCaseTest {

    private lateinit var validateEmailUseCase: ValidateEmailUseCase

    @Before
    fun setUp() {
        validateEmailUseCase = ValidateEmailUseCase()
    }

    @Test
    fun `empty input returns error`() {
        val result = validateEmailUseCase("")
        assertFalse(result.successful)
        assertEquals("Username or Email cannot be empty", result.errorMessage)
    }

    @Test
    fun `blank input returns error`() {
        val result = validateEmailUseCase("   ")
        assertFalse(result.successful)
        assertEquals("Username or Email cannot be empty", result.errorMessage)
    }

    @Test
    fun `invalid email format with at symbol returns error`() {
        val result = validateEmailUseCase("invalid@email")
        assertFalse(result.successful)
        assertEquals("Please enter a valid email address", result.errorMessage)
    }

    @Test
    fun `valid username returns success`() {
        val result = validateEmailUseCase("emilys")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }

    @Test
    fun `valid email returns success`() {
        val result = validateEmailUseCase("user@intellipaat.com")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }
}
