package dev.renheyzer.tazalyk.presentation.screens.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import dev.renheyzer.tazalyk.components.auth.AuthComponent
import dev.renheyzer.tazalyk.presentation.screens.confirm.ConfirmScreen
import dev.renheyzer.tazalyk.presentation.screens.createpassword.CreatePasswordScreen
import dev.renheyzer.tazalyk.presentation.screens.login.LoginScreen
import dev.renheyzer.tazalyk.presentation.screens.signup.SignUpScreen

@Composable
fun AuthUi(modifier: Modifier, authComponent: AuthComponent) {

    val childStack by authComponent.childStack.collectAsState()

    Children(
        modifier = modifier,
        stack = childStack
    ) { child ->
        when (val instance = child.instance) {
            is AuthComponent.Child.SignIn -> LoginScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                component = instance.component
            )

            is AuthComponent.Child.SignUp -> SignUpScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                component = instance.component,
            )

            is AuthComponent.Child.Confirm -> ConfirmScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                component = instance.component
            )

            is AuthComponent.Child.CreatePassword -> CreatePasswordScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                component = instance.component
            )
        }
    }
}