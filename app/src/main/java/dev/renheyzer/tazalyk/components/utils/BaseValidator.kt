package dev.renheyzer.tazalyk.components.utils

abstract class BaseValidator(private val next: Validator? = null) : Validator {

    override fun validate(data: String): Boolean {
        if (this.next != null) {
            next.validate(data)
        }
        return false
    }
}