package dev.renheyzer.tazalyk.components.utils

class BlankValidator(next: Validator? = null) : BaseValidator(next) {

    override fun validate(data: String): Result<String> {
        return if (data.isNotBlank()) {
            Result.success(data)
        } else {
            super.validate(data)
        }
    }
}