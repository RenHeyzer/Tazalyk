package dev.renheyzer.tazalyk.di

import dev.renheyzer.tazalyk.components.signup.utils.EmailValidator
import dev.renheyzer.tazalyk.components.signup.utils.PhoneValidator
import dev.renheyzer.tazalyk.components.utils.BaseValidator
import dev.renheyzer.tazalyk.components.utils.ConfirmPasswordValidator
import dev.renheyzer.tazalyk.components.utils.PasswordValidator
import dev.renheyzer.tazalyk.components.utils.Validator
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    includes(authModule)
}

val authModule = module {

    factory<Validator> { EmailValidator() } withOptions {
        named(AuthQualifiers.EMAIL)
    }

    factory<Validator> {
        PhoneValidator(get<Validator>(named(AuthQualifiers.EMAIL)))
    } withOptions {
        named(AuthQualifiers.PHONE)
    }

    factory<Validator> { (password: String) ->
        ConfirmPasswordValidator(password)
    } withOptions {
        named(AuthQualifiers.CONFIRM_PASSWORD)
    }

    factory<Validator> {
        PasswordValidator(get<BaseValidator>(named(AuthQualifiers.CONFIRM_PASSWORD)))
    } withOptions {
        named(AuthQualifiers.PASSWORD)
    }
}