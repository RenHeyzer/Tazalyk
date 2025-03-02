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
    private val navigateToConfirm: (inputValue: String, isPhoneInput: Boolean) -> Unit,
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
     * Handles the sign-up button click event.
     *
     * @param uiState The current UI state of the [SignUpComponent], containing ui state of
     * `SignUpScreen` and user input data.
     *
     * This function calls `signUp()` on the `viewModel`, passing the current sign-up state,
     * and then navigates to the confirmation screen.
     */
    override fun onSignUpClick() = with(uiState) {
        viewModel.signUp(value)
        navigateToConfirm(value.inputValue, value.isPhoneInput)
    }

    /**
     * Handles the sign-in button click event.
     *
     * This function navigates to the `SignInScreen`
     */
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