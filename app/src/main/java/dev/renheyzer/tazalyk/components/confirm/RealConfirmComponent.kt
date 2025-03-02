package dev.renheyzer.tazalyk.components.confirm

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.retainedInstance

class RealConfirmComponent(
    componentContext: ComponentContext,
     val args: ConfirmArgs,
    private val navigateToCreatePassword: () -> Unit
) : ConfirmComponent, ComponentContext by componentContext {

    private val viewModel = retainedInstance { ConfirmViewModel() }

    override val arguments = args

    override fun onResendCodeClick() {
        viewModel.resendCode()
    }

    override fun onCodeEntered(code: String) {
        viewModel.confirm(code)
        navigateToCreatePassword()
    }

    private class ConfirmViewModel : InstanceKeeper.Instance {

        fun confirm(code: String) {

        }

        fun resendCode() {

        }
    }
}