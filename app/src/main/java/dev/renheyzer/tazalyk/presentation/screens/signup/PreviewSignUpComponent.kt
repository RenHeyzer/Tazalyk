package dev.renheyzer.tazalyk.presentation.screens.signup

import dev.renheyzer.tazalyk.components.signup.SignUpComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class PreviewSignUpComponent : SignUpComponent {
    override val uiState: StateFlow<SignUpComponent.UiState> =
        MutableStateFlow(SignUpComponent.UiState())

    override fun updateInputValue(value: String) {}

    override fun changeInput(input: Boolean) {}

    override fun updateCursorPosition(position: Int) {}

    override fun onSignUpClick(data: String) = Unit

    override fun onSignInClick() = Unit
}