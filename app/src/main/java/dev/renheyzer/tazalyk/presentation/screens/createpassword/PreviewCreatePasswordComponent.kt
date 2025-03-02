package dev.renheyzer.tazalyk.presentation.screens.createpassword

import dev.renheyzer.tazalyk.components.createpassword.CreatePasswordComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PreviewCreatePasswordComponent : CreatePasswordComponent {
    override val uiState: StateFlow<CreatePasswordComponent.UiState> = MutableStateFlow(
        CreatePasswordComponent.UiState()
    )

    override fun updatePassword(password: String) = Unit

    override fun updateConfirmPassword(password: String) = Unit

    override fun onConfirmPasswordClick() = Unit
}