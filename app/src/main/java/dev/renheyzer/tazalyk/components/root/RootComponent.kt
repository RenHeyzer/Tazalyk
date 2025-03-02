package dev.renheyzer.tazalyk.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import dev.renheyzer.tazalyk.components.confirm.ConfirmComponent
import dev.renheyzer.tazalyk.components.createpassword.CreatePasswordComponent
import dev.renheyzer.tazalyk.components.signin.LoginComponent
import dev.renheyzer.tazalyk.components.signup.SignUpComponent
import kotlinx.coroutines.flow.StateFlow

interface RootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed class Child {
        class SignUp(val component: SignUpComponent) : Child()
        class SignIn(val component: LoginComponent) : Child()
        class Confirm(val component: ConfirmComponent) : Child()
        class CreatePassword(val component: CreatePasswordComponent) : Child()
    }
}