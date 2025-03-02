package dev.renheyzer.tazalyk.presentation.screens.login

import dev.renheyzer.tazalyk.components.signin.LoginComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PreviewLoginComponent : LoginComponent {

    override val uiState: StateFlow<LoginComponent.UiState> =
        MutableStateFlow(LoginComponent.UiState())

    override fun updateInputValue(value: String) = Unit

    override fun changeInput(input: Boolean)  = Unit

    override fun updateCursorPosition(position: Int) = Unit

    override fun updatePassword(password: String) = Unit

    override fun onLoginClick() = Unit

    override fun onSignUpClick() = Unit
}