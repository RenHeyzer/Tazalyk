package dev.renheyzer.tazalyk.components.signin

import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

interface LoginComponent {

    @Serializable
    data class UiState(
        val inputValue: String = "",
        val password: String = "",
        val cursorPosition: Int = 0,
        val errorMessage: String = "",
        val isPhoneInput: Boolean = true,
    )

    val uiState: StateFlow<UiState>

    fun updateInputValue(value: String)
    fun changeInput(input: Boolean)
    fun updateCursorPosition(position: Int)
    fun updatePassword(password: String)

    fun onLoginClick()
    fun onSignUpClick()
}