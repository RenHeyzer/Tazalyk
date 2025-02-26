package dev.renheyzer.tazalyk.components.signup

import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

interface SignUpComponent {

    data class Model(
        val email: String = "",
        val phone: String = "",
    )

    @Serializable
    data class UiState(
        val inputValue: String = "",
        val cursorPosition: Int = 0,
        val isError: Boolean = false,
        val isPhoneInput: Boolean = true,
    )

    val uiState: StateFlow<UiState>

    fun updateInputValue(value: String)
    fun changeInput(input: Boolean)
    fun updateCursorPosition(position: Int)

    fun onSignUpClick(uiState: UiState)
    fun onSignInClick()
}