package dev.renheyzer.tazalyk.components.utils

class BlankValidator(private val next: Validator? = null) : BaseValidator(next) {

    override fun validate(data: String): Result<Boolean> {
        return if (data.isNotBlank()) {
            Result.success(true)
        } else {
            super.validate(data)
        }
    }
}