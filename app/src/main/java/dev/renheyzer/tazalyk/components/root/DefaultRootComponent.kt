package dev.renheyzer.tazalyk.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import dev.renheyzer.tazalyk.components.auth.DefaultAuthComponent
import dev.renheyzer.tazalyk.components.utils.toStateFlow
import kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = Config.Auth,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        Config.Auth -> RootComponent.Child.Auth(DefaultAuthComponent(componentContext))
    }


    @Serializable
    sealed class Config {

        @Serializable
        data object Auth : Config()
    }
}