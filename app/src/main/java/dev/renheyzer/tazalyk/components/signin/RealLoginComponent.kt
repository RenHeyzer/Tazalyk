package dev.renheyzer.tazalyk.components.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.statekeeper.saveable
import dev.renheyzer.tazalyk.components.signup.SignUpComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RealLoginComponent(
    componentContext: ComponentContext,
    private val navigateToMain: () -> Unit,
    private val navigateToSignUp: () -> Unit,
) : LoginComponent, ComponentContext by componentContext {

    private val myInstance = instanceKeeper.getOrCreate { MyInstance() }

    /** Save UiState by kill process or change configuration */
    private val _uiState: MutableStateFlow<LoginComponent.UiState> by stateKeeper.saveable(
        serializer = LoginComponent.UiState.serializer(),
        state = { uiState.value }
    ) {
        MutableStateFlow(it ?: LoginComponent.UiState())
    }

    override val uiState = _uiState.asStateFlow()

    override fun updateInputValue(value: String) {
        _uiState.update { it.copy(inputValue = value) }
    }

    override fun changeInput(input: Boolean) {
    }

    override fun updateCursorPosition(position: Int) {
    }

    override fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    override fun onLoginClick() {
        navigateToMain()
    }

    override fun onSignUpClick() {
        navigateToSignUp()
    }

    private class MyInstance : InstanceKeeper.Instance {


        override fun onDestroy() {
            // TODO
        }
    }
}