package dev.renheyzer.tazalyk.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.arkivanov.decompose.defaultComponentContext
import dev.renheyzer.tazalyk.components.root.DefaultRootComponent
import dev.renheyzer.tazalyk.components.root.RootComponent
import dev.renheyzer.tazalyk.presentation.screens.root.RootUi
import dev.renheyzer.tazalyk.ui.theme.TazalykCorner
import dev.renheyzer.tazalyk.ui.theme.TazalykSize
import dev.renheyzer.tazalyk.ui.theme.TazalykStyle
import dev.renheyzer.tazalyk.ui.theme.TazalykTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val rootComponent: RootComponent = DefaultRootComponent(defaultComponentContext())
        setContent {
            val isDarkModeValue = isSystemInDarkTheme()

            val tazalykStyle = remember {
                mutableStateOf(
                    TazalykStyle(
                        textSize = TazalykSize.Medium,
                        corner = TazalykCorner.Medium,
                        isDarkMode = isDarkModeValue
                    )
                )
            }
            TazalykTheme(
                textSize = tazalykStyle.value.textSize,
                darkTheme = tazalykStyle.value.isDarkMode
            ) {
                Scaffold { innerPadding ->
                    RootUi(innerPadding, rootComponent)
                }
            }
        }
    }
}