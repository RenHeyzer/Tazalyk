package dev.renheyzer.tazalyk.components.confirm

interface ConfirmComponent {

    val arguments: ConfirmArgs

    fun onResendCodeClick()
    fun onCodeEntered(code: String)
}