package dev.renheyzer.tazalyk.components.utils

class BlankValidator(private val next: Validator? = null) : BaseValidator(next) {

    override fun validate(data: String): Boolean {
        return if (data.isNotBlank()) {
            true
        } else {
            super.validate(data)
        }
    }
}