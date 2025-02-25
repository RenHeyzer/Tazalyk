package dev.renheyzer.tazalyk.components.utils

class BlankValidator : BaseValidator() {

    override fun validate(data: String): Boolean {
        return if (data.isNotBlank()) {
            true
        } else {
            super.validate(data)
        }
    }
}