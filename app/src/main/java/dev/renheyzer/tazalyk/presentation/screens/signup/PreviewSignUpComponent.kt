package dev.renheyzer.tazalyk.presentation.screens.signup

import dev.renheyzer.tazalyk.components.signup.SignUpComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class PreviewSignUpComponent : SignUpComponent {
    override val uiState: StateFlow<SignUpComponent.UiState> =
        MutableStateFlow(SignUpComponent.UiState())

    override fun updateInputValue(value: String) = Unit

    override fun changeInput(input: Boolean) = Unit

    override fun updateCursorPosition(position: Int) = Unit

    override fun onSignUpClick() = Unit

    override fun onSignInClick() = Unit
}