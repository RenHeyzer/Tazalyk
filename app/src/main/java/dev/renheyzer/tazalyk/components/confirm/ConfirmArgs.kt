package dev.renheyzer.tazalyk.components.confirm

import kotlinx.serialization.Serializable

@Serializable
data class ConfirmArgs(
    val inputValue: String,
    val isPhoneInput: Boolean
)