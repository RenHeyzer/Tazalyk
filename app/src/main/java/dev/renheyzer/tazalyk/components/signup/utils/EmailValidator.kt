package dev.renheyzer.tazalyk.components.signup.utils

import android.util.Patterns
import dev.renheyzer.tazalyk.components.utils.BaseValidator
import dev.renheyzer.tazalyk.components.utils.Validator

class EmailValidator : BaseValidator() {

    override fun validate(data: String): Result<Boolean> {
        return if (!Patterns.EMAIL_ADDRESS.matcher(data).matches())
           Result.failure(IllegalArgumentException("Недопустимый адрес электронной почты"))
        else Result.success(true)
    }
}