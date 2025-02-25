package dev.renheyzer.tazalyk.components.signup.utils

import android.util.Patterns
import dev.renheyzer.tazalyk.components.utils.BaseValidator

class EmailValidator : BaseValidator() {

    override fun validate(data: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(data).matches())
            throw IllegalArgumentException("Недопустимый адрес электронной почты")
        return true
    }
}