package dev.renheyzer.tazalyk.components.createpassword

import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

interface CreatePasswordComponent {

    @Serializable
    data class UiState(
        val password: String = "",
        val confirmPassword: String = "",
        val errorMessage: String = ""
    )

    val uiState: StateFlow<UiState>

    fun updatePassword(password: String)
    fun updateConfirmPassword(password: String)

    fun onConfirmPasswordClick()
}