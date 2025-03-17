package dev.renheyzer.tazalyk.presentation.screens.signup

import dev.renheyzer.tazalyk.components.signup.SignUpComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class PreviewSignUpComponent : SignUpComponent {
    override val uiState: StateFlow<SignUpComponent.UiState> =
        MutableStateFlow(SignUpComponent.UiState())

    override val state: StateFlow<SignUpComponent.Model> =
        MutableStateFlow(SignUpComponent.Model())

    override fun updateInputValue(value: String) = Unit

    override fun changeInput(input: Boolean) = Unit

    override fun updateCursorPosition(position: Int) = Unit
    override fun updatePassword(password: String) = Unit
    override fun updateConfirmPassword(password: String) = Unit

    override fun onSignUpClick() = Unit

    override fun onSignInClick() = Unit
}