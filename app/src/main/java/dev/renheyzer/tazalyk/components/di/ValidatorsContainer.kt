package dev.renheyzer.tazalyk.components.di

import dev.renheyzer.tazalyk.components.auth.utils.EmailValidator
import dev.renheyzer.tazalyk.components.auth.utils.PhoneValidator
import dev.renheyzer.tazalyk.components.utils.PasswordValidator
import dev.renheyzer.tazalyk.components.utils.Validator

class ValidatorsContainer {

    private val emailValidator: Validator = EmailValidator()
    val phoneValidator: Validator = PhoneValidator(emailValidator)
    val passwordValidator: Validator = PasswordValidator()
}