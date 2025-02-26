package dev.renheyzer.tazalyk.components.signup

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.retainedInstance
import com.arkivanov.essenty.statekeeper.saveable
import dev.renheyzer.tazalyk.components.signup.utils.EmailValidator
import dev.renheyzer.tazalyk.components.signup.utils.PhoneValidator
import dev.renheyzer.tazalyk.components.utils.Validator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RealSignUpComponent(
    componentContext: ComponentContext,
    private val navigateToConfirm: () -> Unit,
    private val navigateToSignIn: () -> Unit
) : SignUpComponent, ComponentContext by componentContext {

    private val emailValidator: Validator = EmailValidator()
    private val phoneValidator: Validator = PhoneValidator(emailValidator)

    private val viewModel = retainedInstance { SignUpViewModel(phoneValidator) }

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
    override fun onSignUpClick(uiState: SignUpComponent.UiState) {
        viewModel.signUp(uiState)
        navigateToConfirm()
    }

    override fun onSignInClick() {
        navigateToSignIn()
    }

    private class SignUpViewModel(
        private val phoneValidator: Validator
    ) : InstanceKeeper.Instance {

        private val _state = MutableStateFlow(SignUpComponent.Model())
        val state = _state.asStateFlow()


        fun signUp(uiState: SignUpComponent.UiState) {
            phoneValidator.validate(uiState.inputValue)
        }

        override fun onDestroy() {
            super.onDestroy()

        }
    }
}