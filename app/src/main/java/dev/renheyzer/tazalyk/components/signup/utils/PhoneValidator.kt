package dev.renheyzer.tazalyk.components.signup.utils

import android.util.Patterns
import dev.renheyzer.tazalyk.components.utils.BaseValidator
import dev.renheyzer.tazalyk.components.utils.Validator

class PhoneValidator(private val next: Validator? = null) : BaseValidator(next) {

    override fun validate(data: String): Boolean {
        return if (data[0] == '0') throw IllegalArgumentException("Номер не должен содержать \"0\" в начале")
        else true
    }
}