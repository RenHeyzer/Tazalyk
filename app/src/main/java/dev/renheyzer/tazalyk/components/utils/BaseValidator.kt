package dev.renheyzer.tazalyk.components.utils

abstract class BaseValidator(private val next: Validator? = null) : Validator {

    override fun validate(data: String): Result<String> {
        if (this.next != null) {
            next.validate(data)
        }
        return Result.failure(IllegalStateException("There is no next validator"))
    }
}