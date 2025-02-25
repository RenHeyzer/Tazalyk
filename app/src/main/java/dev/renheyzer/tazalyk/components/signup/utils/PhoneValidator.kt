package dev.renheyzer.tazalyk.components.signup.utils

import dev.renheyzer.tazalyk.components.utils.BaseValidator

class PhoneValidator : BaseValidator() {

    override fun validate(data: String): Boolean {
        return if (data[0] == '0') throw IllegalArgumentException("Номер не должен содержать \"0\" в начале")
        else true
    }
}