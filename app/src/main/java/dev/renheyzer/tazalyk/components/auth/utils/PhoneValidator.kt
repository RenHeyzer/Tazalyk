package dev.renheyzer.tazalyk.components.auth.utils

import android.util.Patterns
import dev.renheyzer.tazalyk.components.utils.BaseValidator
import dev.renheyzer.tazalyk.components.utils.Validator

class PhoneValidator(next: Validator) : BaseValidator(next) {

    override fun validate(data: String): Result<String> {
        if (data[0] == '0')
            return Result.failure(IllegalArgumentException("Номер не должен содержать \"0\" в начале"))
        if (Patterns.EMAIL_ADDRESS.matcher(data).matches()) {
            super.validate(data)
        }
        return Result.success(data)
    }
}