package dev.renheyzer.tazalyk.di

import dev.renheyzer.tazalyk.components.di.ValidatorsContainer
import dev.renheyzer.tazalyk.components.utils.ConfirmPasswordValidator
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule: Module
    get() = module {
        includes(authModule)
    }

val authModule = module {
    factoryOf(::ValidatorsContainer)
    factoryOf(::ConfirmPasswordValidator)
}