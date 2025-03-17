package dev.renheyzer.tazalyk.components.auth

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import dev.renheyzer.tazalyk.components.confirm.ConfirmArgs
import dev.renheyzer.tazalyk.components.confirm.ConfirmComponent
import dev.renheyzer.tazalyk.components.confirm.RealConfirmComponent
import dev.renheyzer.tazalyk.components.createpassword.CreatePasswordComponent
import dev.renheyzer.tazalyk.components.createpassword.RealCreatePasswordComponent
import dev.renheyzer.tazalyk.components.login.LoginComponent
import dev.renheyzer.tazalyk.components.login.RealLoginComponent
import dev.renheyzer.tazalyk.components.signup.RealSignUpComponent
import dev.renheyzer.tazalyk.components.signup.SignUpComponent
import dev.renheyzer.tazalyk.components.utils.toStateFlow
import kotlinx.serialization.Serializable

class DefaultAuthComponent(
    componentContext: ComponentContext
) : AuthComponent, ComponentContext by componentContext {

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
    ): AuthComponent.Child = when (config) {
        Config.Login -> AuthComponent.Child.SignIn(login(componentContext))
        Config.SignUp -> AuthComponent.Child.SignUp(signUp(componentContext))
        is Config.Confirm -> AuthComponent.Child.Confirm(confirm(componentContext, config))
        Config.CreatePassword -> AuthComponent.Child.CreatePassword(createPassword(componentContext))
    }

    private fun login(componentContext: ComponentContext): LoginComponent =
        RealLoginComponent(
            componentContext = componentContext,
            navigateToMain = {},
            navigateToSignUp = {
                navigation.pushNew(Config.SignUp)
            }
        )

    private fun signUp(componentContext: ComponentContext): SignUpComponent =
        RealSignUpComponent(
            componentContext = componentContext,
            navigateToConfirm = { inputValue, isPhoneInput ->
                navigation.pushNew(
                    Config.Confirm(
                        ConfirmArgs(
                            inputValue = inputValue,
                            isPhoneInput = isPhoneInput
                        )
                    )
                )
            },
            navigateToSignIn = {
                navigation.pushNew(Config.Login)
            }
        )

    private fun confirm(
        componentContext: ComponentContext,
        config: Config.Confirm
    ): ConfirmComponent =
        RealConfirmComponent(
            componentContext = componentContext,
            args = config.args,
            navigateToCreatePassword = {
                navigation.pushNew(Config.CreatePassword)
            }
        )

    private fun createPassword(
        componentContext: ComponentContext
    ): CreatePasswordComponent =
        RealCreatePasswordComponent(
            componentContext = componentContext,
            navigateToLogin = {
                navigation.pushNew(Config.Login)
            }
        )

    @Serializable
    sealed class Config {

        @Serializable
        data object SignUp : Config()

        @Serializable
        data object Login : Config()

        @Serializable
        data class Confirm(val args: ConfirmArgs) : Config()

        @Serializable
        data object CreatePassword : Config()
    }
}