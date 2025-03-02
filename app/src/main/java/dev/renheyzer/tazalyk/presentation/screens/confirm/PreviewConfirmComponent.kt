package dev.renheyzer.tazalyk.presentation.screens.confirm

import dev.renheyzer.tazalyk.components.confirm.ConfirmArgs
import dev.renheyzer.tazalyk.components.confirm.ConfirmComponent

class PreviewConfirmComponent : ConfirmComponent {
    override val arguments: ConfirmArgs = ConfirmArgs(inputValue = "+996555555555", isPhoneInput = true)

    override fun onResendCodeClick() = Unit

    override fun onCodeEntered(code: String) = Unit
}