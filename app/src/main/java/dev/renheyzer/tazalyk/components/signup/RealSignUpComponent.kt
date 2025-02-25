package dev.renheyzer.tazalyk.components.signup

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.retainedInstance
import com.arkivanov.essenty.statekeeper.saveable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RealSignUpComponent(
    componentContext: ComponentContext,
    private val navigateToConfirm: () -> Unit,
    private val navigateToSignIn: () -> Unit
) : SignUpComponent, ComponentContext by componentContext {

    private val viewModel = retainedInstance { SignUpViewModel() }

    /** Save UiState by kill process or change configuration */
    private val _uiState: MutableStateFlow<SignUpComponent.UiState> by stateKeeper.saveable(
        serializer = SignUpComponent.UiState.serializer(),
        state = { uiState.value }
    ) {
        MutableStateFlow(it ?: SignUpComponent.UiState())
    }
    override val uiState = _uiState.asStateFlow()

    /**
     * Update input value (phone/email) from OutlinedTextField
     */
    override fun updateInputValue(value: String) {
        _uiState.update { it.copy(inputValue = value) }
    }

    /**
     * Change input type (Phone/Email) of OutlinedTextField
     */
    override fun changeInput(input: Boolean) {
        _uiState.update { it.copy(isPhoneInput = input) }
    }

    /**
     * Update cursor position of OutlinedTextField
     */
    override fun updateCursorPosition(position: Int) {
        _uiState.update { it.copy(cursorPosition = position) }
    }

    /**
     *
     */
    override fun onSignUpClick(data: String) {
        viewModel.signUp(data)
        navigateToConfirm()
    }

    override fun onSignInClick() {
        navigateToSignIn()
    }

    private class SignUpViewModel : InstanceKeeper.Instance {

        private val _state = MutableStateFlow(SignUpComponent.Model())
        val state = _state.asStateFlow()

        fun signUp(data: String) {

        }

        override fun onDestroy() {
            super.onDestroy()

        }
    }
}