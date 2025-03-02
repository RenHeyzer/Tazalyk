package dev.renheyzer.tazalyk.components.utils

class ConfirmPasswordValidator(private val password: String) : BaseValidator() {
    override fun validate(data: String): Result<Boolean> {
        return if (data == password) {
            Result.success(true)
        } else {
            Result.failure(IllegalArgumentException("Пароли не совпадают"))
        }
    }
}