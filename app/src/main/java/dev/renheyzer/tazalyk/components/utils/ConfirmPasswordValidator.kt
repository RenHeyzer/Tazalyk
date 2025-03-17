package dev.renheyzer.tazalyk.components.utils

class ConfirmPasswordValidator {

    fun validate(first: String, second: String): Result<Boolean> {
        return if (first == second) Result.success(true)
        else Result.failure(IllegalArgumentException("Пароли не совпадают"))
    }
}