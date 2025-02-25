package dev.renheyzer.tazalyk.components.utils

abstract class BaseValidator : Validator {

    protected var next: Validator? = null

    override fun validate(data: String): Boolean {
        if (this.next != null) {
            next!!.validate(data)
        }
        return false
    }
}