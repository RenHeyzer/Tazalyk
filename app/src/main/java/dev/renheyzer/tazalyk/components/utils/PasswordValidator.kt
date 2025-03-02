package dev.renheyzer.tazalyk.components.utils

class PasswordValidator(private val next: Validator? = null) : BaseValidator(next) {

    override fun validate(data: String): Result<Boolean> {
        if (data.length < 8) {
            return Result.failure(IllegalArgumentException("Пароль должен содержать минимум 8 символов"))
        }

        if (!data.any { it.isLetter() }) {
            return Result.failure(IllegalArgumentException("Пароль должен содержать как минимум одну цифру"))
        }

        if (!data.any { it.isDigit() }) {
            return Result.failure(IllegalArgumentException("Пароль должен содержать как минимум одну букву"))
        }

        val specialCharacters = "!@#\$%^&*()_+[]{}|;:'\",.<>?/`~"
        if (!data.any { it in specialCharacters }) {
            return Result.failure(IllegalArgumentException("Пароль должен содержать хотя бы один специальный символ"))
        }

        return next?.validate(data) ?: Result.success(true)
    }
}