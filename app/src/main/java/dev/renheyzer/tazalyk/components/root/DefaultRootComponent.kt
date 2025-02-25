package dev.renheyzer.tazalyk.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import dev.renheyzer.tazalyk.components.signin.RealSignInComponent
import dev.renheyzer.tazalyk.components.signin.SignInComponent
import dev.renheyzer.tazalyk.components.signup.RealSignUpComponent
import dev.renheyzer.tazalyk.components.signup.SignUpComponent
import dev.renheyzer.tazalyk.components.utils.toStateFlow
import kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = Config.SignUp,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
            Config.SignIn -> RootComponent.Child.SignIn(signIn(componentContext))
            Config.SignUp -> RootComponent.Child.SignUp(signUp(componentContext))
        }

    private fun signIn(componentContext: ComponentContext): SignInComponent =
        RealSignInComponent(
            componentContext = componentContext,
        )

    private fun signUp(componentContext: ComponentContext): SignUpComponent =
        RealSignUpComponent(
            componentContext = componentContext,
            navigateToConfirm = {},
            navigateToSignIn = {
                navigation.pushNew(Config.SignIn)
            }
        )

    @Serializable
    sealed class Config {

        @Serializable
        data object SignUp : Config()

        @Serializable
        data object SignIn : Config()
    }
}