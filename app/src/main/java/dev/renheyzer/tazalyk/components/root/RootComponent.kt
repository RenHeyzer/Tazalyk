package dev.renheyzer.tazalyk.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import dev.renheyzer.tazalyk.components.signin.SignInComponent
import dev.renheyzer.tazalyk.components.signup.SignUpComponent
import kotlinx.coroutines.flow.StateFlow

interface RootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed class Child {
        class SignUp(val component: SignUpComponent) : Child()
        class SignIn(val component: SignInComponent) : Child()
    }
}