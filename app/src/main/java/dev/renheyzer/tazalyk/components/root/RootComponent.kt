package dev.renheyzer.tazalyk.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import dev.renheyzer.tazalyk.components.auth.AuthComponent
import kotlinx.coroutines.flow.StateFlow

interface RootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed class Child {
        class Auth(val component: AuthComponent) : Child()
    }
}