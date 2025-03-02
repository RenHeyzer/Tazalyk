package dev.renheyzer.tazalyk.components.utils

interface Validator {

    fun validate(data: String): Result<Boolean>
}