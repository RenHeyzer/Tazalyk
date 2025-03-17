package dev.renheyzer.tazalyk.components.signup

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.retainedInstance
import com.arkivanov.essenty.statekeeper.saveable
import dev.renheyzer.tazalyk.components.di.ValidatorsContainer
import dev.renheyzer.tazalyk.components.utils.ConfirmPasswordValidator
import dev.renheyzer.tazalyk.components.utils.Validator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RealSignUpComponent(
    componentContext: ComponentContext,
    private val navigateToConfirm: (inputValue: String, isPhoneInput: Boolean) -> Unit,
    private val navigateToSignIn: () -> Unit
) : SignUpComponent, KoinComponent, ComponentContext by componentContext {

    private val validators by inject<ValidatorsContainer>()
    private val confirmPasswordValidator by inject<ConfirmPasswordValidator>()

    private val viewModel = retainedInstance {
        SignUpViewModel(
            validators.phoneValidator,
            validators.passwordValidator,
            confirmPasswordValidator
        )
    }

    /** Save UiState by kill process or change configuration */
    private val _uiState: MutableStateFlow<SignUpComponent.UiState> by stateKeeper.saveable(
        serializer = SignUpComponent.UiState.serializer(),
        state = { uiState.value }
    ) {
        MutableStateFlow(it ?: SignUpComponent.UiState())
    }

    override val uiState = _uiState.asStateFlow()

    override val state: StateFlow<SignUpComponent.Model> = viewModel.state

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

    override fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    override fun updateConfirmPassword(password: String) {
        _uiState.update { it.copy(confirmPassword = password) }
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
        viewModel.signUp(value) {
            navigateToConfirm(value.inputValue, value.isPhoneInput)
        }
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
        private val phoneValidator: Validator,
        private val passwordValidator: Validator,
        private val confirmPasswordValidator: ConfirmPasswordValidator
    ) : InstanceKeeper.Instance {

        private val _state = MutableStateFlow(SignUpComponent.Model())
        val state = _state.asStateFlow()


        fun signUp(uiState: SignUpComponent.UiState, onSuccess: () -> Unit) {
            phoneValidator.validate(uiState.inputValue)
            confirmPasswordValidator.validate(uiState.password, uiState.confirmPassword).fold(
                onSuccess = {
                    passwordValidator.validate(uiState.password).fold(
                        onSuccess = {
                            onSuccess()
                        },
                        onFailure = {
                            Log.e("sign-up", it.message ?: "Unknown error!")
                        }
                    )
                },
                onFailure = {
                    Log.e("sign-up", it.message ?: "Unknown error!")
                }
            )
        }

        override fun onDestroy() {
            super.onDestroy()

        }
    }
}