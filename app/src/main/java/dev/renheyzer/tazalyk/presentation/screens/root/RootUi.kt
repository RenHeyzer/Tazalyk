package dev.renheyzer.tazalyk.presentation.screens.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import dev.renheyzer.tazalyk.components.root.RootComponent
import dev.renheyzer.tazalyk.presentation.screens.auth.AuthUi
import dev.renheyzer.tazalyk.presentation.screens.confirm.ConfirmScreen
import dev.renheyzer.tazalyk.presentation.screens.createpassword.CreatePasswordScreen
import dev.renheyzer.tazalyk.presentation.screens.login.LoginScreen
import dev.renheyzer.tazalyk.presentation.screens.signup.SignUpScreen
import dev.renheyzer.tazalyk.ui.theme.TazalykTheme

@Composable
fun RootUi(innerPadding: PaddingValues, rootComponent: RootComponent) {

    val childStack by rootComponent.childStack.collectAsState()

    Children(
        modifier = Modifier
            .background(TazalykTheme.colors.primaryBackground)
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(innerPadding),
        stack = childStack
    ) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.Auth -> AuthUi(
                modifier = Modifier
                    .background(TazalykTheme.colors.primaryBackground)
                    .windowInsetsPadding(WindowInsets.systemBars),
                authComponent = instance.component
            )
        }
    }
}