package dev.renheyzer.tazalyk.components.auth.utils

import android.util.Patterns
import dev.renheyzer.tazalyk.components.utils.BaseValidator

class EmailValidator : BaseValidator() {

    override fun validate(data: String): Result<String> {
        return if (!Patterns.EMAIL_ADDRESS.matcher(data).matches())
            Result.failure(IllegalArgumentException("Недопустимый адрес электронной почты"))
        else Result.success(data)
    }
}