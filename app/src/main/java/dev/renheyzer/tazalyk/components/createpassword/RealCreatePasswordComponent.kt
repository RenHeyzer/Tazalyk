package dev.renheyzer.tazalyk.components.createpassword

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.retainedInstance
import com.arkivanov.essenty.statekeeper.saveable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RealCreatePasswordComponent(
    componentContext: ComponentContext,
    private val navigateToLogin: () -> Unit
) : CreatePasswordComponent, ComponentContext by componentContext {

    private val viewModel = retainedInstance { CreatePasswordViewModel() }

    /** Save UiState by kill process or change configuration */
    private val _uiState: MutableStateFlow<CreatePasswordComponent.UiState> by stateKeeper.saveable(
        serializer = CreatePasswordComponent.UiState.serializer(),
        state = { uiState.value }
    ) {
        MutableStateFlow(it ?: CreatePasswordComponent.UiState())
    }

    override val uiState = _uiState.asStateFlow()

    override fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    override fun updateConfirmPassword(password: String) {
        _uiState.update { it.copy(confirmPassword = password) }
    }

    override fun onConfirmPasswordClick() {
        viewModel.createPassword(uiState.value)
        navigateToLogin()
    }

    private class CreatePasswordViewModel : InstanceKeeper.Instance {

        fun createPassword(uiState: CreatePasswordComponent.UiState) {

        }
    }
}