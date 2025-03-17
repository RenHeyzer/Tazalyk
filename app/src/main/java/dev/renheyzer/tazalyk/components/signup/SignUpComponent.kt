package dev.renheyzer.tazalyk.components.signup

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
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
        val errorMessage: String = "",
        val isPhoneInput: Boolean = true,
        val password: String = "",
        val confirmPassword: String = "",
    )

    val uiState: StateFlow<UiState>
    val state: StateFlow<Model>

    fun updateInputValue(value: String)
    fun changeInput(input: Boolean)
    fun updateCursorPosition(position: Int)
    fun updatePassword(password: String)
    fun updateConfirmPassword(password: String)

    fun onSignUpClick()
    fun onSignInClick()
}